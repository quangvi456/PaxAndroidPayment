
package com.rnpaxdemo.payment.constants;

public class Constant {
    public static final int TRANSACTION_SUCCESSED = 100;  //transaction success

    public static final int TRANSACTION_FAILURE = 101; //transaction failure

    public static final int TRANSACTION_TIMEOOUT = 102; //transaction timeout

    public static final int TRANSACTION_STATUS = 103; //transaction timeout

    public static final int PAYMENT_EXTDATA_RESULT = 301;

    public static final int PAYMENT_EXTDATA_ADD_PASSTHRU_RESULT = 302;

    public static final int MANAGE_UPLOAD_IMAGE_RESULT = 501;

    public static final int MANAGE_SAVE_IMAGE_RESULT = 502;

    public static final int MANAGE_VAS_SPECIAL_DATA_RESULT = 503;

    public static final int CONVERGE_ADD_REQUEST_RESULT = 504;

    public static final int CONVERGE_UPDATE_REQUEST_RESULT = 505;

    public static final int MANAGE_VAS_CAP = 506;

    public static final int PAYMENT_TAXDETAIL_RESULT = 303;

    public static final int PAYMENT_COMMERCIAL_RESULT = 304;

    public static final int PAYMENT_LINEIETMDETAIL_RESULT = 305;

    public static final int RESTAURANT_RESULT = 306;

    public static final int PAYMENT_HOST_GATEWAY_RESULT = 307;

    public static final int PAYMENT_TRANSACTION_BEHAVIOR_RESULT = 308;

    public static final int PAYMENT_ORIGINAL_RESULT = 309;

    public static final int PAYMENT_FLEET_CARD_RESULT = 310;

    public static final int PAYMENT_MULTI_MERCHANT_RESULT = 311;

    public static final int PAYMENT_LODGING_RESULT = 312;

    public static final int PAYMENT_LODGING_ROOMS_RESULT = 313;

    public static final int PAYMENT_LODGING_ITEMS_RESULT = 314;

    public static final int PAYMENT_AUTO_RENTAL_ITEMS_RESULT = 314;


    public static final String DIALOG_TITLE = "Title"; //dialog title

    public static final String DIALOG_MESSAGE = "Message"; //dialog message

    public static final String BROADCAST_COMMAND = "com.pax.poslink.command";

    public static final String COMMAND_NAME = "commandname";

    public static final String BROADCAST_PASSTHRU = "com.pax.poslink.passthru";

    public static final String COMMAND_NAME_PASSTHRU = "commandname_passthru";

    public static final String BUNDLE_KEY_EXTDATA = "Payment_ExtData";

    public static final String BUNDLE_KEY_PAYMENT_COMMERCIAL = "Payment_Commercial";

    public static final String BUNDLE_KEY_PAYMENT_TAXDETAIL_DISPLAY = "Payment_TaxDetail_Display";

    public static final String BUNDLE_KEY_PAYMENT_LINEITEM_DISPLAY = "Payment_LineItemDetail_Display";

    public static final String BUNDLE_KEY_PAYMENT_RESTAURANT = "Payment_Restaurant";

    public static final String BUNDLE_KEY_PAYMENT_HOST_GATEWAY = "Payment_HostGateWay";

    public static final String BUNDLE_KEY_PAYMENT_TRANSACTION = "Payment_Transaction_Behavior";

    public static final String BUNDLE_KEY_PAYMENT_ORIGINAL = "Payment_Original";

    public static final String BUNDLE_KEY_PAYMENT_FLEET_CARD = "Payment_FleetCard";

    public static final String BUNDLE_KEY_PAYMENT_MULTI_MERCHANT = "Payment_MultiMerchant";

    public static final String BUNDLE_KEY_PAYMENT_LODGING = "Payment_LODGING";

    public static final String BUNDLE_KEY_PAYMENT_LODGING_ROOMS_DISPLAY = "Payment_RoomsRates_Display";

    public static final String BUNDLE_KEY_PAYMENT_LODGING_ITEMS_DISPLAY = "Payment_Lodging_Items_Display";

    public static final String BROADCAST_SCAN_RESULT_MODE = "com.barcode.sendBroadcast";

    public static final String BUNDLE_KEY_PAYMENT_AUTO_RENTAL = "Payment_Auto_Rental";

    public static final String BUNDLE_KEY_PAYMENT_AUTO_RENTAL_ITEMS_DISPLAY = "Payment_Auto_Rental_Items_Display";

}