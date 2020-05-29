package com.navjot.decemberapplication.ui.termsandconditions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TermsAndConditionsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TermsAndConditionsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Terms Of Use\n" +
                "These terms of use  govern your use of our website www.swiggy.com (the \" Website\") and our \"SabjiApp\" application for mobile and handheld devices (the \" App\"). The Website and the App are jointly referred to as the \" Platform\". Please read these Terms of Use carefully before you use the services. If you do not agree to these Terms of Use, you may not use the services on the Platform, and we request you to uninstall the App. By installing, downloading or even merely using the Platform, you shall be contracting with Swiggy and you signify your acceptance to the Terms of Use and other Swiggy policies (including but not limited to the Cancellation & Refund Policy, Privacy Policy and Take Down Policy) as posted on the Platform from time to time, which takes effect on the date on which you download, install or use the Services, and create a legally binding arrangement to abide by the same.\n" +
                "\n" +
                "The Platform is operated and owned by Bundl Technologies Private Limited, a company incorporated under the Companies Act, 1956 and having its registered office at No. 17/9B, 4th Floor, Maruthi Chambers, Rupena Agrahara, Hosur Road, Bangalore – 560 068. For the purpose of these Terms of Use, wherever the context so requires, \"you\" shall mean any natural or legal person who has agreed to become a buyer or customer on the Platform by providing Registration Data while registering on the Platform as a registered user using any computer systems. The terms \"SabjiApp\", \"we\", \"us\" or \"our\" shall mean Bundl Technologies Private Limited.\n" +
                "\n" +
                "SabjiApp enables transactions between participant restaurants/merchants and buyers, dealing in prepared food and beverages (\" Platform Services\"). The buyers (\" Buyer/s\") can choose and place orders (\" Orders\") from variety of products listed and offered for sale by various neighbourhood merchants including but not limited to the restaurants and eateries (\" Merchant/s\"), on the Platform and SabjiApp enables delivery of such orders at select localities of serviceable cities across India (\" Delivery Services\"). The Platform Services and Delivery Services are collectively referred to as \" Services\". For the delivery services rendered, SabjiApp may charge you delivery fee (inclusive of applicable taxes whenever not expressly mentioned)\n" +
                "\n" +
                "AMENDMENTS\n" +
                "These Terms of Use are subject to modifications at any time. We reserve the right to modify or change these Terms of Use and other Swiggy policies at any time by posting changes on the Platform, and you shall be liable to update yourself of such changes, if any, by accessing the changes on the Platform. You shall, at all times, be responsible for regularly reviewing the Terms of Use and the other SabjiApp policies and note the changes made on the Platform. Your continued usage of the services after any change is posted constitutes your acceptance of the amended Terms of Use and other Swiggy policies. As long as you comply with these Terms of Use, SabjiApp grants you a personal, non-exclusive, non-transferable, limited privilege to access, enter, and use the Platform. By accepting these Terms of Use, you also accept and agree to be bound by the other terms and conditions and Swiggy policies (including but not limited to Cancellation & Refund Policy, Privacy Policy and Notice and Take Down Policy) as may be posted on the Platform from time to time.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}