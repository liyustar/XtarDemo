package com.xtar.demo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.debug.hv.ViewServer;
import com.xtar.demo.fragment.ContentContactFragment;
import com.xtar.demo.fragment.ContentConversationFragment;
import com.xtar.demo.fragment.ContentPluginFragment;
import com.xtar.demo.fragment.ContentSetupFragment;

/**
 * 四个TabWidget实现原TabActivity的效果
 * 
 * @author lyx
 * 
 */
public class TabFragmentActivity extends Activity implements View.OnClickListener {

    private ContentContactFragment contactFragment;
    private ContentConversationFragment conversationFragment;
    private ContentPluginFragment pluginFragment;
    private ContentSetupFragment setupFragment;

    private View contactLayout;
    private View conversationLayout;
    private View pluginLayout;
    private View setupLayout;

    private ImageView contactImage;
    private ImageView conversationImage;
    private ImageView pluginImage;
    private ImageView setupImage;

    private FragmentManager fragmentManager;

    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tabfragment_activity);
        fragmentManager = getFragmentManager();
        initView(savedInstanceState);
        setTabSelection(mIndex);

        // Set content view, etc.
        ViewServer.get(this).addWindow(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (contactFragment != null) {
            fragmentManager.putFragment(outState, "contactFragment", contactFragment);
        }
        if (conversationFragment != null) {
            fragmentManager.putFragment(outState, "conversationFragment", conversationFragment);
        }
        if (pluginFragment != null) {
            fragmentManager.putFragment(outState, "pluginFragment", pluginFragment);
        }
        if (setupFragment != null) {
            fragmentManager.putFragment(outState, "setupFragment", setupFragment);
        }
        outState.putInt("mIndex", mIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        initView(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    private void initView(Bundle savedInstanceState) {
        contactLayout = (View) findViewById(R.id.contact_layout);
        conversationLayout = (View) findViewById(R.id.conversation_layout);
        pluginLayout = (View) findViewById(R.id.plugin_layout);
        setupLayout = (View) findViewById(R.id.setup_layout);

        contactImage = (ImageView) findViewById(R.id.contact_image);
        conversationImage = (ImageView) findViewById(R.id.conversation_image);
        pluginImage = (ImageView) findViewById(R.id.plugin_image);
        setupImage = (ImageView) findViewById(R.id.setup_image);

        contactLayout.setOnClickListener(this);
        conversationLayout.setOnClickListener(this);
        pluginLayout.setOnClickListener(this);
        setupLayout.setOnClickListener(this);

        if (savedInstanceState == null) {
            return;
        }

        contactFragment = (ContentContactFragment) fragmentManager.getFragment(savedInstanceState,
                "contactFragment");
        conversationFragment = (ContentConversationFragment) fragmentManager.getFragment(
                savedInstanceState, "conversationFragment");
        pluginFragment = (ContentPluginFragment) fragmentManager.getFragment(savedInstanceState,
                "pluginFragment");
        setupFragment = (ContentSetupFragment) fragmentManager.getFragment(savedInstanceState,
                "setupFragment");
        mIndex = savedInstanceState.getInt("mIndex");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contact_layout:
                setTabSelection(0);
                break;
            case R.id.conversation_layout:
                setTabSelection(1);
                break;
            case R.id.plugin_layout:
                setTabSelection(2);
                break;
            case R.id.setup_layout:
                setTabSelection(3);
                break;
        }
    }

    private void setTabSelection(int index) {
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case 0:
                contactImage.setImageResource(R.drawable.skin_tab_icon_contact_selected);
                if (contactFragment == null) {
                    contactFragment = new ContentContactFragment();
                    transaction.add(R.id.content, contactFragment);
                } else {
                    transaction.show(contactFragment);
                }
                break;
            case 1:
                conversationImage.setImageResource(R.drawable.skin_tab_icon_conversation_selected);
                if (conversationFragment == null) {
                    conversationFragment = new ContentConversationFragment();
                    transaction.add(R.id.content, conversationFragment);
                } else {
                    transaction.show(conversationFragment);
                }
                break;
            case 2:
                pluginImage.setImageResource(R.drawable.skin_tab_icon_plugin_selected);
                if (pluginFragment == null) {
                    pluginFragment = new ContentPluginFragment();
                    transaction.add(R.id.content, pluginFragment);
                } else {
                    transaction.show(pluginFragment);
                }
                break;
            case 3:
                setupImage.setImageResource(R.drawable.skin_tab_icon_setup_selected);
                if (setupFragment == null) {
                    setupFragment = new ContentSetupFragment();
                    transaction.add(R.id.content, setupFragment);
                } else {
                    transaction.show(setupFragment);
                }
                break;
        }
        transaction.commit();
        mIndex = index;
    }

    private void clearSelection() {
        contactImage.setImageResource(R.drawable.skin_tab_icon_contact_normal);
        conversationImage.setImageResource(R.drawable.skin_tab_icon_conversation_normal);
        pluginImage.setImageResource(R.drawable.skin_tab_icon_plugin_normal);
        setupImage.setImageResource(R.drawable.skin_tab_icon_setup_normal);
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (contactFragment != null) {
            transaction.hide(contactFragment);
        }
        if (conversationFragment != null) {
            transaction.hide(conversationFragment);
        }
        if (pluginFragment != null) {
            transaction.hide(pluginFragment);
        }
        if (setupFragment != null) {
            transaction.hide(setupFragment);
        }
    }

}
