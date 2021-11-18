package com.rnpaxdemo.payment.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Leon.F on 2018/1/23.
 */

public class MainConst {

        public static final List<String> EDC_TYPES = Arrays.asList("ALL", "CREDIT", "DEBIT", "CHECK", "EBT", "GIFT",
                        "LOYALTY", "CASH", "");
        public static final List<String> TRANS_TYPE = Arrays.asList("UNKNOWN", "AUTH", "SALE", "RETURN", "VOID",
                        "POSTAUTH", "FORCEAUTH", "ADJUST", "INQUIRY", "ACTIVATE", "DEACTIVATE", "RELOAD", "VOID SALE",
                        "VOID RETURN", "VOID AUTH", "VOID POSTAUTH", "VOID FORCEAUTH", "VOID WITHDRAWAL", "REVERSAL",
                        "WITHDRAWAL", "ISSUE", "CASHOUT", "REPLACE", "MERGE", "REPORTLOST", "REDEEM", "VERIFY",
                        "REACTIVATE", "FORCED ISSUE", "FORCED ADD", "UNLOAD", "RENEW", "GETCONVERTDETAIL", "CONVERT",
                        "TOKENIZE", "INCREMENTALAUTH", "BALANCEWITHLOCK", "REDEMPTIONWITHUNLOCK", "REWARDS", "REENTER",
                        "TRANSACTION ADJUSTMENT", "TRANSFER");
        public static final List<String> PAYMENT_EDC_TYPES = Arrays.asList("CREDIT", "DEBIT", "CHECK", "EBT_FOODSTAMP",
                        "EBT_CASHBENEFIT", "GIFT", "LOYALTY", "CASH", "EBT", "CHECKCARDTYPE");
        public static final List<String> CARD_TYPES = Arrays.asList("UNKNOWN", "VISA", "MASTERCARD", "AMEX", "DISCOVER",
                        "DINERCLUB", "ENROUTE", "JCB", "REVOLUTIONCARD", "OTHER");
        public static final List<String> SAF_INDICATOR = Arrays.asList("0: New", "1: Failed", "2: All");
        public static final List<String> SAF_INDICATOR_VALUE = Arrays.asList("0", "1", "2");
        public static final List<String> REPORT_TRANS_TYPE = Arrays.asList("LOCALTOTALREPORT", "LOCALDETAILREPORT",
                        "LOCALFAILEDREPORT", "HOSTREPORT", "HISTORYREPORT", "SAFSUMMARYREPORT", "HOSTDETAILREPORT");

        public static final String[] slManageTrans = { "INIT", "GETVAR", "SETVAR", "SHOWDIALOG", "GETSIGNATURE",
                        "SHOWMESSAGE", "CLEARMESSAGE", "RESET", "UPDATEIMAGE", "DOSIGNATURE", "DELETEIMAGE",
                        "SHOWTHANKYOU", "REBOOT", "GETPINBLOCK", "INPUTACCOUNT", "RESETMSR", "INPUTTEXT", "CHECKFILE",
                        "AUTHORIZECARD", "COMPLETEONLINEEMV", "REMOVECARD", "GETEMVTLVDATA", "SETEMVTLVDATA",
                        "INPUTACCOUNTWITHEMV", "COMPLETECONTACTLESSEMV", "SETSAFPARAMETERS", "SHOWTEXTBOX", "REPRINT",
                        "PRINTER", "SHOWITEM", "CARDINSERTDETECTION", "TOKENADMINISTRATIVE", "SHOWDIALOGFORM",
                        "CAMERASCAN", "VASSETMERCHANTPARAMETERS", "VASPUSHDATA", "MIFARECARD", "GETSAFPARAMETERS",
                        "UPLOADFTP", "SESSIONKEYINJECTION", "MACCALCULATION", "GETPEDINFORMATION", "UPDATERESOURCE",
                        "INCREASEKSN" };
        public static final String[] slBatchTrans = { "BATCHCLOSE", "FORCEBATCHCLOSE", "BATCHCLEAR", "PURGEBATCH",
                        "SAFUPLOAD", "DELETESAFFILE", "DELETETRANSACTION" };
        public static final String[] slReportTrans = { "LOCALTOTALREPORT", "LOCALDETAILREPORT", "LOCALFAILEDREPORT",
                        "HOSTREPORT", "HISTORYREPORT", "SAFSUMMARYREPORT", "HOSTDETAILREPORT" };
        public static final String[] slTrans = { "UNKNOWN", "AUTH", "SALE", "RETURN", "VOID", "POSTAUTH", "FORCEAUTH",
                        "CAPTURE", "REPEATSALE", "CAPTUREALL", "ADJUST", "INQUIRY", "ACTIVATE", "DEACTIVATE", "RELOAD",
                        "VOID SALE", "VOID RETURN", "VOID AUTH", "VOID POSTAUTH", "VOID FORCEAUTH", "VOID WITHDRAWAL",
                        "REVERSAL", "WITHDRAWAL", "ISSUE", "CASHOUT", "REPLACE", "MERGE", "REPORTLOST", "REDEEM",
                        "STATUS_CHECK", "SETUP", "INIT", "VERIFY", "REACTIVATE", "FORCED ISSUE", "FORCED ADD", "UNLOAD",
                        "RENEW", "TOKENIZE", "GETCONVERTDETAIL", "CONVERT", "INCREMENTALAUTH", "BALANCEWITHLOCK",
                        "REDEMPTIONWITHUNLOCK", "REWARDS", "REENTER", "TRANSACTION ADJUSTMENT", "TRANSFER" };
        public static final String[] slTrend = { "ALL", "CREDIT", "DEBIT", "CHECK", "EBT_FOODSTAMP", "EBT_CASHBENEFIT",
                        "GIFT", "LOYALTY", "CASH", "EBT", "CHECKCARDTYPE" };
        public static final String[] slCardType = { "UNKNOWN", "VISA", "MASTERCARD", "AMEX", "DISCOVER", "DINERCLUB",
                        "ENROUTE", "JCB", "REVOLUTIONCARD", "VISAFLEET", "MASTERCARDFLEET", "FLEETONE", "FLEETWIDE",
                        "FUELMAN", "GASCARD", "VOYAGER", "WRIGHTEXPRESS", "INTERAC ", "CUP", "OTHER" };
}
