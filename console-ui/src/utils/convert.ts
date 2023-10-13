export const numToUnit = (number:number) => {
    if (number < 1000) {
        return number; // 不需要单位，直接返回原数字
    } else if (number < 1000000) {
        // 小于 1 百万，以 K 为单位
        return (number / 1000).toFixed(1) + 'K';
    } else {
        // 大于等于 1 百万，以 M 为单位
        return (number / 1000000).toFixed(1) + 'M';
    }
}
