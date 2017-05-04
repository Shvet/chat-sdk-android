/*
 * Created by Itzik Braun on 12/3/2015.
 * Copyright (c) 2015 deluge. All rights reserved.
 *
 * Last Modification at: 3/12/15 4:27 PM
 */

package wanderingdevelopment.tk.sdkbaseui.adapter;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import wanderingdevelopment.tk.sdkbaseui.R;
import co.chatsdk.core.defines.Debug;
import com.braunster.chatsdk.utils.volley.VolleyUtils;
import wanderingdevelopment.tk.sdkbaseui.adapter.abstracted.ChatSDKAbstractUsersListAdapter;
import com.braunster.chatsdk.dao.BUser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import timber.log.Timber;

/**
 * Created by itzik on 6/16/2014.
 */
public class ChatSDKUsersListAdapter extends ChatSDKAbstractUsersListAdapter<ChatSDKAbstractUsersListAdapter.AbstractUserListItem> {

    private static final String TAG = ChatSDKUsersListAdapter.class.getSimpleName();
    private static final boolean DEBUG = Debug.UsersWithStatusListAdapter;

    public ChatSDKUsersListAdapter(AppCompatActivity activity) {
        super(activity);
        init();
    }

    public ChatSDKUsersListAdapter(AppCompatActivity activity, boolean isMultiSelect) {
        super(activity, isMultiSelect);
        init();
    }

    public ChatSDKUsersListAdapter(AppCompatActivity activity, List<AbstractUserListItem> listData) {
        super(activity, listData);
        init();
    }

    public ChatSDKUsersListAdapter(AppCompatActivity activity, List<AbstractUserListItem> listData, boolean multiSelect) {
        super(activity, listData, multiSelect);
        init();
    }

    private void init(){
        comparator = new Comparator<AbstractUserListItem>() {
            @Override
            public int compare(AbstractUserListItem lhs, AbstractUserListItem rhs) {
                return lhs.getText().toLowerCase().compareTo(rhs.getText().toLowerCase());
            }
        };
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        row = view;

        final ViewHolder holder;
        final AbstractUserListItem userItem = userItems.get(position);

        // If the row is null or the View inside the row is not good for the current item.
        if (row == null || userItem.getResourceID() != row.getId())
        {
            holder  = new ViewHolder();
            row =  rowForType(holder, position);
        }
        else holder = (ViewHolder) row.getTag();

        holder.textView.setText(userItem.getText());

        if (textColor!=-1991)
            holder.textView.setTextColor(textColor);

        if (getItemViewType(position) == TYPE_USER)
        {
           if (userItem.fromURL)
            {
                int size = holder.profilePicture.getHeight();

                if (userItem.pictureThumbnailURL != null )
                {
                    if (holder.profilePicLoader!=null)
                        holder.profilePicLoader.kill();

                    holder.profilePicLoader = new ProfilePicLoader(holder.profilePicture);

                    VolleyUtils.getImageLoader().get(userItem.pictureThumbnailURL, holder.profilePicLoader, size, size);
                }
                else holder.profilePicture.setImageResource(R.drawable.ic_profile);
            }
            else
            {
                if (DEBUG) Timber.i("Loading profile picture from the db");

                Bitmap bitmap = userItems.get(position).getPicture();
                if (bitmap != null)
                {
                    holder.profilePicture.setImageBitmap(bitmap);
                }
                else
                {
                    holder.profilePicture.setImageResource(R.drawable.ic_profile);
                }
            }

            if (profilePicClickListener!=null)
            {
                holder.profilePicture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        profilePicClickListener.onClick(v, userItem.asBUser());
                    }
                });
            }
        }

        return row;
    }

    @Override
    public void initMaker() {
        itemMaker = new UserListItemMaker<AbstractUserListItem>() {
            @Override
            public AbstractUserListItem fromBUser(BUser user) {
                return  new AbstractUserListItem(R.layout.chat_sdk_row_contact,
                        user.getEntityID(),
                        user.getMetaName(),
                        user.getThumbnailPictureURL(),
                        user.getMetaPictureUrl(),
                        TYPE_USER,
                        user.getOnline() == null ? false : user.getOnline());
            }

            @Override
            public AbstractUserListItem getHeader(String type) {
                return new AbstractUserListItem(R.layout.chat_sdk_list_header, type, TYPE_HEADER);
            }

            @Override
            public List<AbstractUserListItem> getListWithHeaders(List<AbstractUserListItem> list) {


                List<AbstractUserListItem> listData = new ArrayList<AbstractUserListItem>();

                List<AbstractUserListItem> onlineUsers = new ArrayList<AbstractUserListItem>();
                List<AbstractUserListItem> offlineUsers = new ArrayList<AbstractUserListItem>();

                sortList(onlineUsers);
                sortList(offlineUsers);

                for (AbstractUserListItem user: list){
                    if (user.online) {
                        onlineUsers.add(user);
                    }
                    else offlineUsers.add(user);
                }

                if (onlineUsers.size() == 0 && offlineUsers.size() == 0)
                {
                    listData.add(getHeader(H_NO_CONTACTS));
                }
                else if (onlineUsers.size() == 0){
                    listData.add(getHeader(H_NO_ONLINE));
                    listData.add(getHeader(getHeaderWithSize(H_OFFLINE, offlineUsers.size())));
                    listData.addAll(offlineUsers);
                }
                else if (offlineUsers.size() == 0){
                    listData.add(getHeader(getHeaderWithSize(H_ONLINE, onlineUsers.size())));
                    listData.addAll(onlineUsers);
                    listData.add(getHeader(H_NO_OFFLINE));
                }
                else {
                    listData.add(getHeader(getHeaderWithSize(H_ONLINE, onlineUsers.size())));
                    listData.addAll(onlineUsers);
                    listData.add(getHeader(getHeaderWithSize(H_OFFLINE, offlineUsers.size())));
                    listData.addAll(offlineUsers);
                }

                return listData;
            }
        };
    }

}