package com.navjot.decemberapplication.ui.send;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.navjot.decemberapplication.R;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;
    TextView call, sms;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
//    final TextView textView = root.findViewById(R.id.text_send);
//        sendViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        call = root.findViewById(R.id.call);
        sms = root.findViewById(R.id.sms);
        final String num = "8146264528";
        if (ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},1);
        }
        if (ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.SEND_SMS},0);
        }



        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + num));
                startActivity(callIntent);
            }

        });


        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Getting intent and PendingIntent instance
               // Intent smsIntent=new Intent(getActivity(),SendFragment.class);
//                Intent smsIntent=new Intent(Intent.ACTION_SEND);
//                PendingIntent pi=PendingIntent.getActivity(getActivity(), 0, smsIntent,0);
//
//                 //Get the SmsManager instance and call the sendTextMessage method to send message
//                SmsManager sms=SmsManager.getDefault();
//                sms.sendTextMessage("8568808287", null, "hello I am Navjot", null,null);
//
//                if (ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED)
//                {
//                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.SEND_SMS},0);
//                }
//
//                startActivity(smsIntent);
                //Getting intent and PendingIntent instance
              //  Intent intent=new Intent(getActivity(),MainPageActivity.class);
              //  PendingIntent pi=PendingIntent.getActivity(getActivity(), 0, intent,0);
/*
                //Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(num, null, "hello", pi,null);

                Toast.makeText(getActivity(), "Message Sent successfully!",
                        Toast.LENGTH_LONG).show();
                if (ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.SEND_SMS},0);
               }
*/


                Intent smsIntent = new Intent();
                smsIntent.setAction(Intent.ACTION_SEND);
                smsIntent.putExtra(Intent.EXTRA_TEXT, "Type your message here");
                smsIntent.setType("text/plain");
                startActivity(Intent.createChooser(smsIntent, "Sms via"));
            }
        });


//
//
//                    onRequestPermissionsResult(int requestCode,String[]permissions,int[]grantResults)
//        {
//            if(requestCode==1 && grantResults[0]!=PackageManager.PERMISSION_GRANTED)
//            {
//                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CALL_PHONE}, 1);
//            }
//            else
//            {
//               // Toast.makeText(this,"permission granted",Toast.LENGTH_SHORT).show();
//            }
//        }



return root;

    }
}