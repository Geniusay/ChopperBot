interface Config {
  theme: ThemeConfig.Config;
  locales: any;
  currency: CurrencyConfig.Config;
}

declare namespace CurrencyConfig {
  interface Currency {
    label: string;
    decimalDigits: number;
    decimalSeparator: string;
    thousandsSeparator: string;
    currencySymbol: string;
    currencySymbolNumberOfSpaces: number;
    currencySymbolPosition: string;
  }

  interface Config {
    currency: Currency;
    availableCurrencies: Currency[];
  }
}

declare namespace ThemeConfig {
  interface Config {
    //primary color
    primary: string;

    //follow OS theme
    followOs: boolean;

    // global theme for the theme
    globalTheme: string;

    // side menu theme, use global theme or custom
    menuTheme: string;

    // toolbar theme, use global theme or custom
    toolbarTheme: string;

    // show toolbar detached from top
    isToolbarDetached: boolean;

    // wrap pages content with a max-width
    isContentBoxed: boolean;

    // application is right to left
    isRTL: boolean;

    // dark theme colors
    dark: import("vuetify").ThemeDefinition;

    // light theme colors
    light: import("vuetify").ThemeDefinition;
  }
}

declare namespace NavigationConfig {
  interface Menu {
    icon?: string;
    key?: string;
    text?: string;
    link?: string;
    regex?: RegExp;
    disabled?: boolean;
    items?: NonNullable<Menu[]>;
  }

  interface Config {
    menu: Menu[];
    footer: Footer[];
  }

  interface Footer {
    text?: string;
    key: string;
    href?: string;
    target?: string;
  }
}
