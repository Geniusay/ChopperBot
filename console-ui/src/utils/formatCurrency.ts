import configs from "@/configs";
/**
 * Format a number to currency format
 * @param value
 * @param currency
 */
export const formatCurrency = (
  value: number,
  currency?: CurrencyConfig.Currency
): number | string => {
  const { currency: currencyConfig } = configs;

  let c: CurrencyConfig.Currency;
  c = currency || currencyConfig.currency;

  return formatPrice(value, c);
};

/**
 * Format a number to currency format
 * @param price
 * @param currency
 */
export const formatPrice = (
  price: number,
  currency: CurrencyConfig.Currency
) => {
  try {
    const numberFormatted = numberFormat(
      price,
      currency.decimalDigits,
      currency.decimalSeparator,
      currency.thousandsSeparator
    );

    if (currency.currencySymbol) {
      const priceSeparator =
        currency.currencySymbolNumberOfSpaces > 0
          ? " ".repeat(currency.currencySymbolNumberOfSpaces)
          : "";
      let priceParts = [
        numberFormatted,
        priceSeparator,
        currency.currencySymbol,
      ];

      if (currency.currencySymbolPosition === "left") {
        priceParts = priceParts.reverse();
      }

      return priceParts.join("");
    } else {
      return numberFormatted;
    }
  } catch (e) {
    return price;
  }
};

/**
 * Format a number to currency format
 * @param number
 * @param decimals
 * @param dec_point
 * @param thousands_sep
 */
export const numberFormat = (
  number: number,
  decimals: number,
  dec_point: string,
  thousands_sep: string
) => {
  if (isNaN(number)) {
    return number;
  }

  const negative = number < 0;

  if (negative) number = number * -1;
  let strArr: string[] = [];
  strArr = number
    .toFixed(decimals ? decimals : 0)
    .toString()
    .split(".");

  const parts: string[] = [];

  for (let i = strArr[0].length; i > 0; i -= 3) {
    parts.unshift(strArr[0].substring(Math.max(0, i - 3), i));
  }

  strArr[0] = parts.join(thousands_sep ? thousands_sep : ",");

  return (negative ? "-" : "") + strArr.join(dec_point ? dec_point : ".");
};
