export const nanosToHMS_CN = (milliseconds: number) => {
  const totalSeconds = milliseconds / 1000;
  const hours = Math.floor(totalSeconds / 3600);
  const minutes = Math.floor((totalSeconds % 3600) / 60);
  const seconds = totalSeconds % 60;

  return `${hours}小时${minutes}分钟${seconds}秒`;
}

export const nanosToFormat = (timestamp: number) => {
  const date = new Date(timestamp);

  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const day = date.getDate().toString().padStart(2, '0');
  const hours = date.getHours().toString().padStart(2, '0');
  const minutes = date.getMinutes().toString().padStart(2, '0');
  const seconds = date.getSeconds().toString().padStart(2, '0');

  const formattedDate = `${year}-${month}-${day}`;
  const formattedTime = `${hours}:${minutes}:${seconds}`;

  return formattedDate + ' ' + formattedTime;
}


export function isoStrToNormal(isoString: string): string {
  const date = new Date(isoString);

  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const day = date.getDate().toString().padStart(2, '0');
  const hours = date.getHours().toString().padStart(2, '0');
  const minutes = date.getMinutes().toString().padStart(2, '0');
  const seconds = date.getSeconds().toString().padStart(2, '0');
  const milliseconds = date.getMilliseconds().toString().padStart(3, '0');

  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}.${milliseconds}`;
}

export function getTimeAgo(input: string): string {
  const currentTime = new Date();
  const inputTime = new Date(input);

  const timeDiff = currentTime.getTime() - inputTime.getTime();
  const seconds = Math.floor(timeDiff / 1000);
  const minutes = Math.floor(seconds / 60);
  const hours = Math.floor(minutes / 60);
  const days = Math.floor(hours / 24);

  if (days > 0) {
    return days === 1 ? '1 day ago' : `${days} days ago`;
  } else if (hours > 0) {
    return hours === 1 ? '1 hr ago' : `${hours} hrs ago`;
  } else if (minutes > 0) {
    return minutes === 1 ? '1 min ago' : `${minutes} min ago`;
  } else if (seconds > 0) {
    return seconds === 1 ? '1 sec ago' : `${seconds} sec ago`;
  } else {
    return 'now';
  }
}

/**
 * 将number类型的时间转换成“00:00:00:00”格式
 */
function padZero(num: number, size = 2): string {
  let s = num.toString();
  while (s.length < size) s = '0' + s;
  return s;
}
export function formatDuration(durationInSeconds: number): string {
  const totalMilliseconds = Math.round(durationInSeconds * 1000); // 四舍五入到最近的毫秒
  const seconds = Math.floor((totalMilliseconds / 1000) % 60);
  const minutes = Math.floor((totalMilliseconds / (1000 * 60)) % 60);
  const hours = Math.floor((totalMilliseconds / (1000 * 60 * 60)) % 24);
  const milliseconds = ('00' + (totalMilliseconds % 1000)).slice(-2); // 保留毫秒的前两位

  return `${padZero(hours)}:${padZero(minutes)}:${padZero(seconds)}:${milliseconds}`;
}
