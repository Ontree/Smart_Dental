package com.edu.thss.smartdental;

import java.util.ArrayList;

import com.edu.thss.smartdental.adapter.BBSListAdapter;
import com.edu.thss.smartdental.adapter.ImgListAdapter;
import com.edu.thss.smartdental.model.BBSElement;
import com.edu.thss.smartdental.model.ImageElement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

public class BBSInTabViewFragment extends Fragment {
	private BBSListAdapter bbsAdapter;
	private EditText editText;
	private ListView list;
	private ArrayList<BBSElement> posts;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs_in_view, container, false);
		editText = (EditText)rootView.findViewById(R.id.bbs_searchbox);
		editText.addTextChangedListener(filterTextWatcher);
		list = (ListView)rootView.findViewById(R.id.bbs_list);
		initImages();
		bbsAdapter = new BBSListAdapter(posts,this.getActivity().getApplicationContext());
		list.setAdapter(bbsAdapter);
		return rootView;
	}
	
	
	private TextWatcher filterTextWatcher = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable s) {
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			bbsAdapter.getFilter().filter(s);	
		}
	};

	private void initImages(){
		posts = new ArrayList<BBSElement>();
		BBSElement i = new BBSElement("天气不错","今天风速74km/h，吹得我牙痛","2011-1-15","张三");
		posts.add(i);
		i = new BBSElement("啦啦啦","我喜欢这个医生","2011-1-15","李四");
		posts.add(i);
		i = new BBSElement("花花花","拔牙愉快啦啦","2012-1-25","二娃");
		posts.add(i);
		i = new BBSElement("呵呵呵","我牙齿很白","2013-1-25","奥千");
		posts.add(i);
	}
}
