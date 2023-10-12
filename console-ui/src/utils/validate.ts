export const strRules = [
  (v)=> !!v || '不能为空',
  (v)=> v!==''|| '不能为空',
]

export const timeRules = [
  (v)=> !!v || "时间必须存在",
  (v)=> v > 0 || "时间必须大于0",
  (v)=> v >= 1000 || "时间必须大于1s",
]
