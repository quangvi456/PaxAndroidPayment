import {NativeModules} from 'react-native';

const PaymentModule = NativeModules.PaymentModule;

/**
 * @typedef {"UART" | "TCP" | "SSL" | "HTTP" | "HTTPS" | "BLUETOOTH" | "USB" | "AIDL"} CommonSettingType
 */

/**
 * @typedef {"CREDIT" | "DEBIT" | "CHECK" | "EBT_FOODSTAMP" | "EBT_CASHBENEFIT" | "GIFT" | "LOYALTY" | "CASH" | "EBT" | "CHECKCARDTYPE"} TenderType
 */

/**
 * @typedef {"UNKNOWN" |"AUTH" |"SALE" |"RETURN" |"VOID" |"POSTAUTH" |"FORCEAUTH" |"ADJUST" |"INQUIRY" |"ACTIVATE" |"DEACTIVATE" |"RELOAD" |"VOID SALE" |"VOID RETURN" |"VOID AUTH" |"VOID POSTAUTH" |"VOID FORCEAUTH" |"VOID WITHDRAWAL" |"REVERSAL" |"WITHDRAWAL" |"ISSUE" |"CASHOUT" |"REPLACE" |"MERGE" |"REPORTLOST" |"REDEEM" |"VERIFY" |"REACTIVATE" |"FORCED ISSUE" |"FORCED ADD" |"UNLOAD" |"RENEW" |"GETCONVERTDETAIL" |"CONVERT" |"TOKENIZE" |"INCREMENTALAUTH" |"BALANCEWITHLOCK" |"REDEMPTIONWITHUNLOCK" |"REWARDS" |"REENTER" |"TRANSACTION ADJUSTMENT" |"TRANSFER"} TransType
 */

/**
 *
 * @typedef {Object} ConnectParams
 * @property {CommonSettingType} type
 * @property {number} timeout
 * @property {string} serialPort
 * @property {string} baudRate
 * @property {string} destIP
 * @property {string} destPort
 * @property {boolean=} enableProxy
 */

/**
 *
 * @param {ConnectParams} params
 * @returns {Promise<string>}
 */
export function connect({
  type,
  timeout,
  serialPort,
  baudRate,
  destIP,
  destPort,
  enableProxy,
}) {
  return new Promise((resolve, reject) => {
    PaymentModule.init({
      type,
      timeout,
      serialPort,
      baudRate,
      destIP,
      destPort,
      enableProxy,
    })
      .then(status => resolve(status))
      .catch(error => reject(error));
  });
}

function parseExtData(result) {
  const extDataStr = result.ExtData ?? '';
  let extData = {};
  extData.BatchNum = extDataStr.includes('BatchNum')
    ? extDataStr.match(/<BatchNum>(.*)<\/BatchNum>/)[1]
    : '';
  extData.AmountDue = extDataStr.includes('AmountDue')
    ? extDataStr.match(/<AmountDue>(.*)<\/AmountDue>/)[1]
    : '';
  extData.TipAmount = extDataStr.includes('TipAmount')
    ? extDataStr.match(/<TipAmount>(.*)<\/TipAmount>/)[1]
    : '';
  extData.CashBackAmount = extDataStr.includes('CashBackAmount')
    ? extDataStr.match(/<CashBackAmount>(.*)<\/CashBackAmount>/)[1]
    : '';
  extData.MerchantFee = extDataStr.includes('MerchantFee')
    ? extDataStr.match(/<MerchantFee>(.*)<\/MerchantFee>/)[1]
    : '';
  extData.TaxAmount = extDataStr.includes('TaxAmount')
    ? extDataStr.match(/<TaxAmount>(.*)<\/TaxAmount>/)[1]
    : '';
  extData.PLEntryMode = extDataStr.includes('PLEntryMode')
    ? extDataStr.match(/<PLEntryMode>(.*)<\/PLEntryMode>/)[1]
    : '';
  extData.ExpDate = extDataStr.includes('ExpDate')
    ? extDataStr.match(/<ExpDate>(.*)<\/ExpDate>/)[1]
    : '';
  extData.PLNameOnCard = extDataStr.includes('PLNameOnCard')
    ? extDataStr.match(/<PLNameOnCard>(.*)<\/PLNameOnCard>/)[1]
    : '';
  extData.PLCardPresent = extDataStr.includes('PLCardPresent')
    ? extDataStr.match(/<PLCardPresent>(.*)<\/PLCardPresent>/)[1]
    : '';
  extData.ECRRefNum = extDataStr.includes('ECRRefNum')
    ? extDataStr.match(/<ECRRefNum>(.*)<\/ECRRefNum>/)[1]
    : '';
  extData.EDCTYPE = extDataStr.includes('EDCTYPE')
    ? extDataStr.match(/<EDCTYPE>(.*)<\/EDCTYPE>/)[1]
    : '';
  extData.CARDBIN = extDataStr.includes('CARDBIN')
    ? extDataStr.match(/<CARDBIN>(.*)<\/CARDBIN>/)[1]
    : '';
  extData.PROGRAMTYPE = extDataStr.includes('PROGRAMTYPE')
    ? extDataStr.match(/<PROGRAMTYPE>(.*)<\/PROGRAMTYPE>/)[1]
    : '';
  extData.SN = extDataStr.includes('SN')
    ? extDataStr.match(/<SN>(.*)<\/SN>/)[1]
    : '';
  extData.GlobalUID = extDataStr.includes('GlobalUID')
    ? extDataStr.match(/<GlobalUID>(.*)<\/GlobalUID>/)[1]
    : '';
  extData.TC = extDataStr.includes('TC')
    ? extDataStr.match(/<TC>(.*)<\/TC>/)[1]
    : '';
  extData.TVR = extDataStr.includes('TVR')
    ? extDataStr.match(/<TVR>(.*)<\/TVR>/)[1]
    : '';
  extData.AID = extDataStr.includes('AID')
    ? extDataStr.match(/<AID>(.*)<\/AID>/)[1]
    : '';
  extData.TSI = extDataStr.includes('TSI')
    ? extDataStr.match(/<TSI>(.*)<\/TSI>/)[1]
    : '';
  extData.ATC = extDataStr.includes('ATC')
    ? extDataStr.match(/<ATC>(.*)<\/ATC>/)[1]
    : '';
  extData.APPLAB = extDataStr.includes('APPLAB')
    ? extDataStr.match(/<APPLAB>(.*)<\/APPLAB>/)[1]
    : '';
  extData.APPPN = extDataStr.includes('APPPN')
    ? extDataStr.match(/<APPPN>(.*)<\/APPPN>/)[1]
    : '';
  extData.IAD = extDataStr.includes('IAD')
    ? extDataStr.match(/<IAD>(.*)<\/IAD>/)[1]
    : '';
  extData.CVM = extDataStr.includes('IAD')
    ? extDataStr.match(/<CVM>(.*)<\/CVM>/)[1]
    : '';
  extData.userLanguageStatus = extDataStr.includes('userLanguageStatus')
    ? extDataStr.match(/<userLanguageStatus>(.*)<\/userLanguageStatus>/)[1]
    : '';

  return extData;
}

/**
 *
 * @typedef {Object} SaleParams
 * @property {number} amount
 * @property {number} tip
 * @property {TenderType} tenderType
 * @property {string} ecrRefNum
 */

const saleResponseExample = {
  ECRTransID: '',
  IssuerResponseCode: '',
  PaymentService2000: '',
  Track2Data: '',
  HostDetailedMessage: '',
  AuthorizationResponse: '',
  DebitAccountType: '',
  Track1Data: '',
  TransactionRemainingAmount: '',
  HostCode: '88888888',
  GiftCardType: '',
  GatewayTransactionID: '',
  TransactionIntegrityClass: '',
  SignData: '',
  ResultTxt: 'OK',
  RawResponse: '',
  ExtraBalance: '',
  RequestedAmount: '1',
  RemainingBalance: '',
  ExtData: {
    BatchNum: '1',
    AmountDue: '0',
    TipAmount: '0',
    CashBackAmount: '0',
    MerchantFee: '0',
    TaxAmount: '0',
    PLEntryMode: '2',
    ExpDate: '0525',
    PLNameOnCard: ' /',
    PLCardPresent: '0',
    ECRRefNum: '1',
    EDCTYPE: 'CREDIT',
    CARDBIN: '445093',
    PROGRAMTYPE: '0',
    SN: '1440002443',
    GlobalUID: '1440002443202111172129232267',
    TC: '0AD51F7771B910EE',
    TVR: '0000000000',
    AID: 'A0000000031010',
    TSI: '0000',
    ATC: '0156',
    APPLAB: 'HSBC VISA CARD',
    APPPN: 'HSBC PAYWAVE',
    IAD: '06021203A00000',
    CVM: '7',
    userLanguageStatus: '1',
  },
  Track3Data: '',
  BogusAccountNum: '6588',
  RefNum: '6',
  ResultCode: '000000',
  HostResponse: '0',
  MaskedPAN: '',
  Message: 'DEMO APPROVED',
  RetrievalReferenceNumber: '',
  SigFileName: '',
  CardType: 'VISA',
  AvsResponse: '',
  CardInfo: {
    ProgramType: '0',
    NewCardBin: '',
    CardBin: '445093',
  },
  Timestamp: '20211117212923',
  CvResponse: '',
  AuthCode: '000000',
  EDCType: '',
  ApprovedAmount: '1',
};

/**
 *
 * @param {SaleParams} params
 * @param {({ status: number, message: string, }) => void=} onProcessCallback
 * @returns {Promise<typeof saleResponseExample>}
 */
export function sale({amount, tip, tenderType, ecrRefNum}, onProcessCallback) {
  return new Promise((resolve, reject) => {
    /**
     * @type {TransType}
     */
    const transType = 'SALE';
    PaymentModule.sale({
      amount: Number(amount),
      tip: Number(tip),
      tenderType,
      transType,
      ecrRefNum,
    })
      .then(result => {
        result.ExtData = parseExtData(result.ExtData);
        resolve(result);
      })
      .catch(error => reject(error));
  });
}

/**
 *
 * @typedef {Object} VoidParams
 * @property {string} refNum
 * @property {TenderType} tenderType
 * @property {string} ecrRefNum
 */
/**
 *
 * @param {VoidParams} params
 * @returns {Promise<typeof saleResponseExample>}
 */
export function voidPayment({refNum, tenderType, ecrRefNum}) {
  return new Promise((resolve, reject) => {
    /**
     * @type {TransType}
     */
    const transType = 'VOID';
    PaymentModule.voidPayment({
      refNum,
      tenderType,
      ecrRefNum,
      transType,
    })
      .then(result => {
        result.ExtData = parseExtData(result.ExtData);
        resolve(result);
      })
      .catch(error => reject(error));
  });
}

export function closeBatch() {
  return new Promise((resolve, reject) => {
    PaymentModule.closeBatch()
      .then(result => resolve(result))
      .catch(error => reject(error));
  });
}

export function cancel() {
  return new Promise((resolve, reject) => {
    PaymentModule.cancel()
      .then(result => resolve(result))
      .catch(error => reject(error));
  });
}

/**
 *
 * @typedef {Object} AdjustParams
 * @property {string} refNum
 * @property {TenderType} tenderType
 * @property {string} ecrRefNum
 * @property {number} amount
 */
/**
 *
 * @param {AdjustParams} params
 * @returns {Promise<typeof saleResponseExample>}
 */
export function adjust({refNum, tenderType, ecrRefNum, amount}) {
  return new Promise((resolve, reject) => {
    /**
     * @type {TransType}
     */
    const transType = 'ADJUST';
    PaymentModule.adjust({
      refNum,
      tenderType,
      ecrRefNum,
      transType,
      amount,
    })
      .then(result => {
        result.ExtData = parseExtData(result.ExtData);
        resolve(result);
      })
      .catch(error => reject(error));
  });
}

/**
 *
 * @typedef {Object} RefundParams
 * @property {TenderType} tenderType
 * @property {string} ecrRefNum
 * @property {number} amount
 */
/**
 *
 * @param {RefundParams} params
 * @returns {Promise<typeof saleResponseExample>}
 */
export function refund({tenderType, ecrRefNum, amount}) {
  return new Promise((resolve, reject) => {
    /**
     * @type {TransType}
     */
    const transType = 'RETURN';
    PaymentModule.refund({
      tenderType,
      ecrRefNum,
      transType,
      amount,
    })
      .then(result => {
        result.ExtData = parseExtData(result.ExtData);
        resolve(result);
      })
      .catch(error => reject(error));
  });
}
