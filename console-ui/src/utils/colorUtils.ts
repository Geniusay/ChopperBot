const isHex = (hex: string) => /^#(?:[A-Fa-f0-9]{3}){1,2}$/.test(hex);
// Function name: isHex
// Parameters: hex
// Purpose: Checks if the parameter is a valid hexadecimal color
// Return: boolean

const isRGB = (rgb: string) =>
  /^rgb[(](?:\s*0*(?:\d\d?(?:\.\d+)?(?:\s*%)?|\.\d+\s*%|100(?:\.0*)?\s*%|(?:1\d\d|2[0-4]\d|25[0-5])(?:\.\d+)?)\s*(?:,(?![)])|(?=[)]))){3}[)]$/.test(
    rgb
  );
// Function name: isRGB
// Parameters: rgb
// Purpose: Checks if the parameter is a valid RGB color
// Return: boolean

const isRGBA = (rgba: string) =>
  /^^rgba[(](?:\s*0*(?:\d\d?(?:\.\d+)?(?:\s*%)?|\.\d+\s*%|100(?:\.0*)?\s*%|(?:1\d\d|2[0-4]\d|25[0-5])(?:\.\d+)?)\s*,){3}\s*0*(?:\.\d+|1(?:\.0*)?)\s*[)]$/.test(
    rgba
  );
// Function name: isRGBA
// Parameters: rgba
// Purpose: Checks if the parameter is a valid RGBA color
// Return: boolean

const parseRGB = (rgb: string) => rgb.match(/\((.*?)\)/)?.[1].split(",");
// Function name: parseRGB
// Parameters: rgb
// Purpose: Parses the RGB color into an array of numbers
// Return: Array

const hexToRGB = (hex: string) =>
  hex
    .replace(
      /^#?([a-f\d])([a-f\d])([a-f\d])$/i,
      (m, r, g, b) => "#" + r + r + g + g + b + b
    )
    .substring(1)
    .match(/.{2}/g)
    ?.map((x) => parseInt(x, 16));
// Function name: hexToRGB
// Parameters: hex
// Purpose: Converts a hex color to an RGB color
// Return: Array

export const setOpacity = (color: string, opacity: number) => {
  const [r, g, b] = (isHex(color) ? hexToRGB(color) : parseRGB(color)) ?? [
    0, 0, 0,
  ];

  return "rgba" + "(" + r + "," + g + "," + b + "," + opacity + ")";
};

export const isValidColor = (color: string) =>
  isHex(color) || isRGB(color) || isRGBA(color);

export const colorShade = (color: string, percentage: number) => {
  // Check if the color is light or dark
  const lighten = percentage < 0;
  // Calculate the base color
  const base = lighten ? 1 + percentage : 1 - percentage;
  // Calculate the shade
  const shade = lighten ? 0 : percentage * 255 ** 2;
  // Calculate the primary color
  const calcPrimary = (p: string | number) =>
    Math.round((base * Math.floor(Number(p)) ** 2 + shade) ** 0.5);

  // Parse the color to rgb
  const [r, g, b, a] = (isHex(color) ? hexToRGB(color) : parseRGB(color)) ?? [
    0, 0, 0,
  ];
  // Calculate the red color
  const red = calcPrimary(r);
  // Calculate the green color
  const green = calcPrimary(g);
  // Calculate the blue color
  const blue = calcPrimary(b);

  // Return the color
  return (
    "rgb" +
    (a ? "a(" : "(") +
    red +
    "," +
    green +
    "," +
    blue +
    (a ? "," + a : ")")
  );
};
