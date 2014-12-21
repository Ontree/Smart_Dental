/*
 * author: Chen Minghai
 */
package com.edu.thss.smartdental;

import java.util.HashMap;
import java.util.List;

import com.edu.thss.smartdental.RemoteDB.DBUtil;
import com.readystatesoftware.viewbadger.BadgeView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.support.v4.app.FragmentManager;

public class BBSInFragment extends Fragment {
	
	private RadioGroup radioGroup;
	private FragmentManager fragmentManager;
	private int UserId;
	private BadgeView badge;
	
	public BBSInFragment(int id) {
		UserId = id;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs_in, container,false);
		
		fragmentManager = getFragmentManager();
		radioGroup = (RadioGroup)rootView.findViewById(R.id.bbs_in_tab);
		View button = rootView.findViewById(R.id.remind_button);
		setBadgeView(button);
		radioGroup.check(R.id.bbs_in_tab_view);
		changeFragment(0);
		RadioButton manageTag = (RadioButton)rootView.findViewById(R.id.bbs_in_tab_manage);
		Button manageInvisibleTag = (Button) rootView.findViewById(R.id.manage_invisible_button);
		String user_name = getActivity().getSharedPreferences("setting", Activity.MODE_PRIVATE).getString("username", "");
		if(!user_name.equals("qq")){
			manageTag.setVisibility(View.GONE);
			manageInvisibleTag.setVisibility(View.GONE);
		}
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch(checkedId){
				case R.id.bbs_in_tab_view: 
					changeFragment(0); 
					break;
				case R.id.bbs_in_tab_group:
					changeFragment(1);
					break;
				case R.id.bbs_in_tab_news:
					changeFragment(2);
					break;
				case R.id.bbs_in_tab_post:
					changeFragment(3);
					break;
				case R.id.bbs_in_tab_manage:
					changeFragment(4);
					break;
				//TODO ADD OTHER FRAGMENT
				}
               
			}} );
		
		return rootView;
	}
	
	private void setBadgeView(View view) {
		badge = new BadgeView(getActivity(), view);
		DBUtil db = new DBUtil();
		String userName = getActivity().getSharedPreferences("setting", Activity.MODE_PRIVATE).getString("username", "");
		List<HashMap<String, String>> newsList = db.selectAllUnreadNewsByUsername(userName);
		int num = newsList.size() - 1;
		badge.setText(Integer.toString(num));
		badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
		badge.setTextColor(Color.WHITE);
		badge.setBadgeBackgroundColor(Color.RED);
		badge.setTextSize(12);
		badge.setBadgeMargin(5);
		if (num > 0) {
			badge.show();
		}
	}
	
	private void changeFragment(int index){
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		Fragment tempfragment = null;
		switch(index){
		case 0:
			tempfragment = new BBSInTabViewFragment(this.UserId);
			break;
		case 1:
			//tempfragment = new BBSInTabSearchFragment();
			tempfragment = new BBSFragment();
			break;
		case 2:
			badge.hide();
			tempfragment = new BBSInTabNewsFragment();
			break;
		case 3:
			tempfragment = new BBSInTabPostFragment();
			break;
		case 4:
			tempfragment = new BBSInTabManageFragment();
			break;
		}
		if (tempfragment != null) {
			transaction.replace(R.id.bbs_in_tab_content, tempfragment);
			transaction.commit();
        }
	}

}