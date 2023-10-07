export const nanosToHMS_CN = (milliseconds: number) => {
  const totalSeconds = milliseconds / 1000;
  const hours = Math.floor(totalSeconds / 3600);
  const minutes = Math.floor((totalSeconds % 3600) / 60);
  const seconds = totalSeconds % 60;

  return `${hours}小时${minutes}分钟${seconds}秒`;
}
