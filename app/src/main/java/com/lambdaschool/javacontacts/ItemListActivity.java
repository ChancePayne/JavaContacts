package com.lambdaschool.javacontacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lambdaschool.javacontacts.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        public static final int CACHE_SIZE = 15;

        private final ItemListActivity mParentActivity;
        private final List<Contact> mValues;
        private final CircularDictionary<String, Bitmap> imageCache;
        private final boolean                      mTwoPane;

        private final View.OnClickListener         mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact item = (Contact) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.getName());
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.item_detail_container, fragment)
                                   .commit();
                } else {
                    Context context = view.getContext();
                    Intent  intent  = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.getName());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      boolean twoPane) {
            mValues = new ArrayList<>();
            imageCache = new CircularDictionary<>(CACHE_SIZE);
            getContacts();
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        private void getContacts() {
            ContactDao.getContacts(new ContactDao.ContactCallback() {
                @Override
                public void processContacts(List<Contact> contacts) {
                    mValues.addAll(contacts);
                    mParentActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    });
                }
            });
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                                      .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Contact contact = mValues.get(position);
            holder.mIdView.setText(contact.getName());
            holder.mContentView.setText(contact.getPhone());

            final Bitmap contactImage = imageCache.get(contact.getImageThumbnail());
            if(contactImage == null) {
                // get image
                holder.mImageView.setImageDrawable(mParentActivity.getDrawable(R.drawable.ic_launcher_background));
                NetworkAdapter.backgroundBitmapFromUrl(contact.getImageThumbnail(), new NetworkAdapter.NetworkImageCallback() {
                    @Override
                    public void processImage(final Bitmap image) {
                        imageCache.put(contact.getImageThumbnail(), image);

                        mParentActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                holder.mImageView.setImageBitmap(image);
                            }
                        });
                    }
                });
            } else {
                // set image
                holder.mImageView.setImageBitmap(contactImage);
            }


            holder.itemView.setTag(contact);
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;
            final ImageView mImageView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
                mImageView = view.findViewById(R.id.image);
            }
        }
    }
}
