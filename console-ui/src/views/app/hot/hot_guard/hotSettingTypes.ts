export interface Setting{
  id:number;
  platform: string;
  failRetryTimes: number;
  enableHotModule: boolean;
  enableHotLive: boolean;
  followDogEnable: boolean;
  updateHotModuleTimes: number;
  updateHotLivesTimes: number;
}
