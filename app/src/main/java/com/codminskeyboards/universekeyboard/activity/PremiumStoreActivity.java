package com.codminskeyboards.universekeyboard.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;
import com.codminskeyboards.universekeyboard.R;
import com.codminskeyboards.universekeyboard.utils.GlobalClass;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONObject;

import java.util.ArrayList;

public class PremiumStoreActivity extends AppCompatActivity implements View.OnClickListener {

    public InterstitialAd interstitialAd;
    // IabHelper iabHelper;
    String currentPlan;
    String payload = "";
    static final String SKU_GET_ADDS = "universekeyboard.inapp.removead";
    static final String TAG = "SubscriptionTAG";
    IInAppBillingService iInAppBillingService;
    static final String SKU_GET_BACKGROUND = "universekeyboard.inapp.texualcolorbg";
    static final String SKU_GET_THEME = "universekeyboard.inapp.theamslotes";
    static final String SKU_GET_COLORS = "universekeyboard.inapp.colors";
    static final String SKU_GET_FONTS = "universekeyboard.inapp.fonts";
    static final String SKU_GET_SOUNDS = "universekeyboard.inapp.sounds";
//    IabHelper.OnConsumeFinishedListener consumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
//        public void onConsumeFinished(Purchase purchase, IabResult result) {
//            GlobalClass.printLog(TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);
//
//            // if we were disposed of in the meantime, quit.
//            // if (iabHelper == null) return;
//
//            // We know this is the "gas" sku because it's the only one we consume,
//            // so we don't check which sku was consumed. If you have more than one
//            // sku, you probably should check...
//            if (result.isSuccess()) {
//                // successfully consumed, so we apply the effects of the item in our
//                // game world's logic, which in our case means filling the gas tank a bit
//                GlobalClass.printLog(TAG, "Consumption successful. Provisioning.");
//            } else {
//                complain("Error while consuming: " + result);
//            }
//            //updateUi();
//            GlobalClass.printLog(TAG, "End consumption flow.");
//        }
//    };

    static final int RC_REQUEST1 = 501;
    static final int RC_REQUEST2 = 502;
    static final int RC_REQUEST3 = 503;
    static final int RC_REQUEST4 = 504;
    static final int RC_REQUEST5 = 505;
    static final int RC_REQUEST6 = 506;

    boolean isremoove_adds = false;
    boolean isbackground = false;
    boolean istheme_slot = false;
    boolean iscolors = false;
    boolean isfonts = false;
    boolean issounds = false;
    //    public IabHelper.OnIabPurchaseFinishedListener purchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
//        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
//            GlobalClass.printLog(TAG, "Purchase finished: " + result + ", purchase: " + purchase);
//
//            if we were disposed of in the meantime, quit.
//            if (iabHelper == null) return;
//
//            if (result.isFailure()) {
//                complain("Error purchasing: " + result);
//                return;
//            }
//
//            if (!verifyDeveloperPayload(purchase)) {
//                complain("Error purchasing. Authenticity verification failed.");
//                return;
//            }
//
//            GlobalClass.printLog(TAG,currentPlan +"Purchase successful.");
//
//    updateUserPlan();
//    updateUi();
//
//     iabHelper.consumeAsync(purchase, consumeFinishedListener);
//
//            switch(purchase.getSku())
//
//    {
//        case "universekeyboard.inapp.removead":
//            GlobalClass.setPreferencesBool(getApplicationContext(), GlobalClass.key_isAdLock, false);
//            break;
//        case "universekeyboard.inapp.texualcolorbg":
//            GlobalClass.setPreferencesBool(getApplicationContext(), GlobalClass.key_isBackgroundLock, false);
//            break;
//        case "universekeyboard.inapp.theamslotes":
//            GlobalClass.setPreferencesBool(getApplicationContext(), GlobalClass.key_isThemeLock, false);
//            break;
//        case "universekeyboard.inapp.colors":
//            GlobalClass.setPreferencesBool(getApplicationContext(), GlobalClass.key_isColorLock, false);
//            break;
//        case "universekeyboard.inapp.fonts":
//            GlobalClass.setPreferencesBool(getApplicationContext(), GlobalClass.key_isFontLock, false);
//            break;
//        case "universekeyboard.inapp.sounds":
//            GlobalClass.setPreferencesBool(getApplicationContext(), GlobalClass.key_isSoundLock, false);
//            break;
//    }
//
//}
//    };
//
//        List<String> arrSKUs = Arrays.asList(SKU_GET_ADDS, SKU_GET_BACKGROUND, SKU_GET_COLORS, SKU_GET_SOUNDS, SKU_GET_FONTS, SKU_GET_THEME);
//    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
//        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
//            GlobalClass.printLog(TAG, "Query inventory finished.");
//
//            // Have we been disposed of in the meantime? If so, quit.
//            if (iabHelper == null)
//                return;
//
//            // Is it a failure?
//            if (result.isFailure()) {
//                complain("Failed to query inventory: " + result);
//                return;
//            }
//
//            GlobalClass.printLog(TAG, "Query inventory was successful.");
//
//            Purchase getAddPurchase = inventory.getPurchase(SKU_GET_ADDS);
//            isremoove_adds = (getAddPurchase != null && verifyDeveloperPayload(getAddPurchase));
//            GlobalClass.printLog(TAG, "User " + (isremoove_adds ? "HAS" : "DOES NOT HAVE") + " Purchase Yearly Subsription.");
//            GlobalClass.printLog("getYearPurchase====================", "" + getAddPurchase);
//            if (getAddPurchase != null && verifyDeveloperPayload(getAddPurchase)) {
//                iabHelper.consumeAsync(getAddPurchase, consumeFinishedListener);
//            }
//
//            Purchase getBackgroundPurchase = inventory.getPurchase(SKU_GET_BACKGROUND);
//            isbackground = (getBackgroundPurchase != null && verifyDeveloperPayload(getBackgroundPurchase));
//            GlobalClass.printLog(TAG, "User " + (isbackground ? "HAS" : "DOES NOT HAVE") + " Purchase Monthly Subscription.");
//            GlobalClass.printLog("getBackgroundPurchase====================", "" + getBackgroundPurchase);
//            if (getBackgroundPurchase != null && verifyDeveloperPayload(getBackgroundPurchase)) {
//                iabHelper.consumeAsync(getBackgroundPurchase, consumeFinishedListener);
//            }
//            Purchase getthemePurchase = inventory.getPurchase(SKU_GET_THEME);
//            istheme_slot = (getthemePurchase != null && verifyDeveloperPayload(getthemePurchase));
//            GlobalClass.printLog(TAG, "User " + (istheme_slot ? "HAS" : "DOES NOT HAVE") + " Purchase Monthly Subscription.");
//            GlobalClass.printLog("getthemePurchase====================", "" + getthemePurchase);
//            if (getthemePurchase != null && verifyDeveloperPayload(getthemePurchase)) {
//                iabHelper.consumeAsync(getthemePurchase, consumeFinishedListener);
//            }
//            Purchase getColorPurchase = inventory.getPurchase(SKU_GET_COLORS);
//            iscolors = (getColorPurchase != null && verifyDeveloperPayload(getColorPurchase));
//            GlobalClass.printLog(TAG, "User " + (iscolors ? "HAS" : "DOES NOT HAVE") + " Purchase Monthly Subscription.");
//            GlobalClass.printLog("getColorPurchase====================", "" + getColorPurchase);
//            if (getColorPurchase != null && verifyDeveloperPayload(getColorPurchase)) {
//                iabHelper.consumeAsync(getColorPurchase, consumeFinishedListener);
//            }
//            Purchase getFontPurchase = inventory.getPurchase(SKU_GET_FONTS);
//            isfonts = (getFontPurchase != null && verifyDeveloperPayload(getFontPurchase));
//            GlobalClass.printLog(TAG, "User " + (isfonts ? "HAS" : "DOES NOT HAVE") + " Purchase Monthly Subscription.");
//            GlobalClass.printLog("getFontPurchase====================", "" + getFontPurchase);
//            if (getFontPurchase != null && verifyDeveloperPayload(getFontPurchase)) {
//                iabHelper.consumeAsync(getFontPurchase, consumeFinishedListener);
//            }
//            Purchase getSoundPurchase = inventory.getPurchase(SKU_GET_FONTS);
//            issounds = (getSoundPurchase != null && verifyDeveloperPayload(getSoundPurchase));
//            GlobalClass.printLog(TAG, "User " + (issounds ? "HAS" : "DOES NOT HAVE") + " Purchase Monthly Subscription.");
//            GlobalClass.printLog("getSoundPurchase====================", "" + getSoundPurchase);
//            if (getSoundPurchase != null && verifyDeveloperPayload(getSoundPurchase)) {
//                iabHelper.consumeAsync(getSoundPurchase, consumeFinishedListener);
//            }
//
//
//            GlobalClass.printLog(TAG, "Initial inventory query finished; enabling main UI.");
//        }
//    };
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_premium_store);

        ImageView backImageView = findViewById(R.id.backImageView);
        TextView restoreTextView = findViewById(R.id.restoreTextView);
        TextView remove_ads = findViewById(R.id.remove_ads);
        TextView backgrounds = findViewById(R.id.backgrounds);
        TextView theme_slot = findViewById(R.id.theme_slot);
        TextView colors = findViewById(R.id.colors);
        TextView fonts = findViewById(R.id.fonts);
        TextView sounds = findViewById(R.id.sounds);

        backImageView.setOnClickListener(this);
        restoreTextView.setOnClickListener(this);

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqgyXolVCkrSdFsdembldwrpGHmXPSvvA7mdegRUzufvziVIS9JtVnGS20EbmFTKcPLzyfwoXPSNbwvmKHJg7RnoiqcrQ4QbtkhsHmMO7paA+akHFTPQGLHN6TW5invO33A3VBu/hxMTj9jHr9jr0tGJWj5cWITc2BkUfHcD8SFkSUca/ruQRJg3DTWMqMRqSnTeGccQJBRx+sCU8MxYlp3BwwOyvEdmeCFsnhPLHRmk3MXv/JgVr3oEQylakq3PkNvDVXbO5GHRYR8bKD2YXVZ+56FsCxT4t3sQXCQQ84zp1tKN/nFm9pDAlXqEf9T1MQFZVriBzI8XsZCraLoVrVwIDAQAB";

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_full_screen));
        interstitialAd.loadAd(new AdRequest.Builder().build());


//        iabHelper = new IabHelper(PremiumStoreActivity.this, base64EncodedPublicKey);
//        iabHelper.enableDebugLogging(true);
//        iabHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
//            public void onIabSetupFinished(IabResult result) {
//                GlobalClass.printLog(TAG, "Setup finished.");
//
//                if (!result.isSuccess()) {
//                    // Oh noes, there was a problem.
//                    complain("Problem setting up in-app billing: " + result);
//                    return;
//                }
//
//                // Have we been disposed of in the meantime? If so, quit.
//                if (iabHelper == null)
//                    return;
//
//                // IAB is fully set up. Now, let's get an inventory of stuff we own.
//                GlobalClass.printLog(TAG, "Setup successful. Querying inventory.");
//                iabHelper.queryInventoryAsync(mGotInventoryListener);
//            }
//        });

        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {
                iInAppBillingService = null;
            }

            @Override
            public void onServiceConnected(ComponentName name,
                                           IBinder service) {
                iInAppBillingService = IInAppBillingService.Stub.asInterface(service);
            }
        };

        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        if (GlobalClass.getPreferencesBool(getApplicationContext(), GlobalClass.key_isColorLock, true))
            setAdMob();

//        remove_ads.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    if (iabHelper != null) {
//                        iabHelper.flagEndAsync();
//                        iabHelper.launchPurchaseFlow(PremiumStoreActivity.this, SKU_GET_ADDS, RC_REQUEST1, purchaseFinishedListener, payload);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

//        backgrounds.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    if (iabHelper != null) {
//                        iabHelper.flagEndAsync();
//                        iabHelper.launchPurchaseFlow(PremiumStoreActivity.this, SKU_GET_BACKGROUND, RC_REQUEST2, purchaseFinishedListener, payload);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        });
//
//        theme_slot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    if (iabHelper != null) {
//                        iabHelper.flagEndAsync();
//                        iabHelper.launchPurchaseFlow(PremiumStoreActivity.this, SKU_GET_THEME, RC_REQUEST3, purchaseFinishedListener, payload);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        colors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    if (iabHelper != null) {
//                        iabHelper.flagEndAsync();
//                        iabHelper.launchPurchaseFlow(PremiumStoreActivity.this, SKU_GET_COLORS, RC_REQUEST4, purchaseFinishedListener, payload);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        fonts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    if (iabHelper != null) {
//                        iabHelper.flagEndAsync();
//                        iabHelper.launchPurchaseFlow(PremiumStoreActivity.this, SKU_GET_FONTS, RC_REQUEST5, purchaseFinishedListener, payload);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        sounds.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    if (iabHelper != null) {
//                        iabHelper.flagEndAsync();
//                        iabHelper.launchPurchaseFlow(PremiumStoreActivity.this, SKU_GET_SOUNDS, RC_REQUEST6, purchaseFinishedListener, payload);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        restoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Bundle ownedItems = iInAppBillingService.getPurchases(3, getPackageName(), "inapp", null);

                    // Check response
                    int responseCode = ownedItems.getInt("RESPONSE_CODE");
                    if (responseCode != 0)
                        throw new Exception("Error");

                    // Get the list of purchased items
                    ArrayList<String> purchaseDataList =
                            ownedItems.getStringArrayList("INAPP_PURCHASE_DATA_LIST");

                    ArrayList<String> ownedSkus = ownedItems.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");

                    Toast.makeText(PremiumStoreActivity.this, "Restore Successfully ", Toast.LENGTH_SHORT).show();

                    int flagI = 0;
                    assert purchaseDataList != null;
                    for (String purchaseData : purchaseDataList) {
                        JSONObject o = new JSONObject(purchaseData);
                        String purchaseToken = o.optString("token", o.optString("purchaseToken"));
//                        Consume purchaseToken, handling any errors
//
//                        Toast.makeText(PremiumStoreActivity.this, "Consumed: " + ownedSkus.get(flagI), Toast.LENGTH_SHORT).show();
//
//                        int response = iInAppBillingService.consumePurchase(3, getPackageName(), purchaseToken);
//
//                        if (response == 0) {
//                        Log.d("Consumed", "Consumed");
//
//                        Toast.makeText(PremiumStoreActivity.this, "Consumed: " + ownedSkus.get(flagI), Toast.LENGTH_SHORT).show();
//                        } else {
//                        Log.d("", "No" + response);
//                        Toast.makeText(PremiumStoreActivity.this, ""+purchaseToken, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(PremiumStoreActivity.this, "NOT: " + ownedSkus.get(flagI), Toast.LENGTH_SHORT).show();
//                        }
                        flagI++;

                    }

//                    for (final String singleSKU : arrSKUs) {
//                        String purchaseToken = "inapp:" + getPackageName() + ":"+singleSKU;
//                        try {
//                            Log.d("","Running");
//                            Toast.makeText(PremiumStoreActivity.this, "Running", Toast.LENGTH_SHORT).show();
//                            int response = iInAppBillingService.consumePurchase(3, getPackageName(), "android.test.puchased");
//                            if(response==0)
//                            {
//                                Log.d("Consumed","Consumed");
//
//                                Toast.makeText(PremiumStoreActivity.this, "Consumed: "+singleSKU, Toast.LENGTH_SHORT).show();
//                            }else {
//                                Log.d("","No"+response);
//                                Toast.makeText(PremiumStoreActivity.this, ""+purchaseToken, Toast.LENGTH_SHORT).show();
//                                Toast.makeText(PremiumStoreActivity.this, "NOT: "+singleSKU, Toast.LENGTH_SHORT).show();
//                            }
//                        }catch (RemoteException e)
//                        {
//                            Log.d("Errorr",""+e);
//                            Toast.makeText(PremiumStoreActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                        }
//
//                        Log.e("SKUs", "onClick: "+arrSKUs );
//
//                        Log.e("SKUs", "onClick: "+singleSKU );
//
//                        mIabHelper.queryInventoryAsync(true, arrSKUs, mGotInventoryListener);
//
//                        Listener that's called when we finish querying the items and subscriptions we own
//                        IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
//                            public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
//
//                                Log.e("SKUs", "inonClick: "+arrSKUs );
//
//                                Log.e("SKUs", "inonClick: "+singleSKU );
//                                Log.d("PAY", "Query inventory finished.");
//
//                                // Have we been disposed of in the meantime? If so, quit.
//                                if (mIabHelper == null) return;
//                                Purchase purchase = inventory.getPurchase(singleSKU);
//
//                                Log.e("SKUs", "onQueryInventoryFinished: "+singleSKU );
//                                if (purchase != null) {
//                                    //purchased
//                                    Toast.makeText(PremiumStoreActivity.this, "Purchased"+singleSKU, Toast.LENGTH_SHORT).show();
//                                }else {
//                                    Toast.makeText(PremiumStoreActivity.this, "NotPurchased"+singleSKU, Toast.LENGTH_SHORT).show();
//
//                                }
//                            }
//                        };
//                    }
//
//
//                    Bundle ownedItems = iInAppBillingService.getPurchases(3, "com.codminskeyboards.universekeyboard", "inapp", null);
//                    Toast.makeText(PremiumStoreActivity.this, "ownedItems: " + ownedItems.size(), Toast.LENGTH_SHORT).show();
//                    int response = ownedItems.getInt("RESPONSE_CODE");
//                    Log.e("7887", "onClick: "+response );
//                    if (response == 0) {
//
//                        ArrayList<String> ownedSkus =
//                                ownedItems.getStringArrayList("universekeyboard.inapp.removead");
//                        ArrayList<String> ownedSkus1 =
//                                ownedItems.getStringArrayList("universekeyboard.inapp.texualcolorbg");
//                        ArrayList<String> ownedSkus2 =
//                                ownedItems.getStringArrayList("universekeyboard.inapp.theamslotes");
//                        ArrayList<String> ownedSkus3 =
//                                ownedItems.getStringArrayList("universekeyboard.inapp.colors");
//                        ArrayList<String> ownedSkus4 =
//                                ownedItems.getStringArrayList("universekeyboard.inapp.fonts");
//                        ArrayList<String> ownedSkus5 =
//                                ownedItems.getStringArrayList("universekeyboard.inapp.sounds");
//                        ArrayList<String> purchaseDataList =
//                                ownedItems.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
//                        ArrayList<String> signatureList =
//                                ownedItems.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
//
//                        Log.e("454545454545", "onClick: "+purchaseDataList );
//                        Log.e("454545454545", "onClick: "+signatureList );
//
//                        Log.e("454545454545", "onClick1: "+ownedSkus1 );
//                        Log.e("454545454545", "onClick2: "+ownedSkus2);
//                        Log.e("454545454545", "onClick3: "+ownedSkus3 );
//                        Log.e("454545454545", "onClick4: "+ownedSkus4 );
//                        Log.e("454545454545", "onClick5: "+ownedSkus5 );
//                        String continuationToken =
//                                ownedItems.getString("INAPP_CONTINUATION_TOKEN");
//
//                        String allPurchasedProduct = "";
//                        for (int i = 0; i < ownedItems.size(); i++) {
//                            String purchaseData = purchaseDataList.get(i);
//                            String signature = signatureList.get(i);
//                            String sku = ownedSkus.get(i);
//                            String sku1 = ownedSkus1.get(i);
//                            String sku2 = ownedSkus2.get(i);
//                            String sku3 = ownedSkus3.get(i);
//                            String sku4 = ownedSkus4.get(i);
//                            String sku5 = ownedSkus5.get(i);
//                            allPurchasedProduct = allPurchasedProduct + sku + ", ";
//                            // do something with this purchase information
//                            // e.g. display updated list of products owned by user
//                        }
//
//
//                        GlobalClass.printLog("allPurchasedProduct: ", allPurchasedProduct);
//                        Log.e("12121", "onClick: "+ownedSkus );
//                        Toast.makeText(PremiumStoreActivity.this, "PurchasedProduct: " + ownedSkus.size(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(PremiumStoreActivity.this, "allPurchasedProduct: " + ownedSkus, Toast.LENGTH_SHORT).show();
//
//                        // if continuationToken != null, call getPurchases again
//                        // and pass in the token to retrieve more items
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setAdMob() {
        adView = findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                adView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdClosed() {
                adView.setVisibility(View.GONE);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                adView.setVisibility(View.GONE);
            }

        });
    }

//    boolean verifyDeveloperPayload(Purchase purchase) {
//        String payload = purchase.getDeveloperPayload();

        /*
         * TODO: verify that the developer payload of the purchase is correct. It will be
         * the same one that you sent when initiating the purchase.
         *
         * WARNING: Locally generating a random string when starting a purchase and
         * verifying it here might seem like a good approach, but this will fail in the
         * case where the user purchases an item on one device and then uses your app on
         * a different device, because on the other device you will not have access to the
         * random string you originally generated.
         *
         * So a good developer payload has these characteristics:
         *
         * 1. If two different users purchase an item, the payload is different between them,
         *    so that one user's purchase can't be replayed to another user.
         *
         * 2. The payload must be such that you can verify it even when the app wasn't the
         *    one who initiated the purchase flow (so that items purchased by the user on
         *    one device work on other devices owned by the user).
         *
         * Using your own server to store and verify developer payloads across app
         * installations is recommended.
         */
//
//        return true;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        GlobalClass.printLog("PremiumActivity", "onActivityResult" + requestCode + "," + resultCode + "," + data);

//        if (requestCode == 1001) {
//
//            Global.printLog(" PremiumActivity ", "-----requestCode------10001--" + requestCode);
//
//            int responseCode = data.getIntExtra("RESPONSE_CODE", 0);
//            String purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");
//            String dataSignature = data.getStringExtra("INAPP_DATA_SIGNATURE");
//
//            if (resultCode == RESULT_OK) {
//
//                Global.printLog("PremiumActivity", "-----requestCode------RESULT_OK--" + resultCode);
//
//                try {
//                    JSONObject jo = new JSONObject(purchaseData);
//                    String productId = jo.getString("productId");
//                    String purchaseToken = jo.getString("purchaseToken");
//                    String packageName = jo.getString("packageName");
//
//                    Global.printLog("PremiumActivity", "-------JSONObject----------" + jo.toString());
//
//                    ArrayList<TestPayData> arrayList = new ArrayList<TestPayData>();
//
//                    TestPayData testPayData = new TestPayData();
//                    testPayData.setOrderId(jo.getString("orderId"));
//                    testPayData.setPackageName(jo.getString("packageName"));
//                    testPayData.setProductId(jo.getString("productId"));
//                    testPayData.setPurchaseTime(jo.getString("purchaseTime"));
//                    testPayData.setPurchaseState(jo.getString("purchaseState"));
//                    testPayData.setDeveloperPayload(jo.getString("developerPayload"));
//                    testPayData.setPurchaseToken(jo.getString("purchaseToken"));
//
//                    sessionManager.saveTestArrayList(arrayList);
//
//                    callGoogleReceipt(purchaseToken, productId, packageName);
//
//                } catch (JSONException e) {
//                    Global.printLog("PremiumActivity","Failed to parse purchase data.");
//                    e.printStackTrace();
//                }
//            }
//        }
//
//
//      Global.showToast(getApplicationContext(), "=====requestCode=====" +requestCode + "\n=====resultCode=====" + resultCode + "\n=====data=====" + data);
//
//        if (iabHelper != null && !iabHelper.handleActivityResult(requestCode, resultCode, data)) {
//            GlobalClass.printLog("on Activity", " this is on activity result --- with data finsh ");
//
//            // Global.showToast(getApplicationContext(), "=====requestCode11=====" +requestCode + "\n=====resultCode11=====" + resultCode + "\n=====data11=====" + data);
//            // not handled, so handle it ourselves (here's where you'd perform any handling of activity results not related to in-app billing...
//
//            super.onActivityResult(requestCode, resultCode, data);
//        } else
//            GlobalClass.printLog(TAG, "onActivityResult handled by IABUtil.");
    }

    void complain(String message) {
        GlobalClass.printLog(TAG, "**** TrivialDrive Error: " + message);
        GlobalClass.printLog(" PremiumActivity", "Error: " + message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

//        GlobalClass.printLog(TAG, "Destroying helper.");
//        if (iabHelper != null) {
//            try {
//                iabHelper.dispose();
//            } catch (IllegalArgumentException ex) {
//                ex.printStackTrace();
//            }
//            iabHelper = null;
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backImageView:
                finish();

                interstitialAd.loadAd(new AdRequest.Builder().build());
                if (interstitialAd.isLoaded())
                    interstitialAd.show();

                break;
        }
    }
}