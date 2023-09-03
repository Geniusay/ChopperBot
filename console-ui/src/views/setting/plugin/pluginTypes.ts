export interface Plugin{
  pluginName: string;
  pluginModule: string;
  pluginName_CN: string;
  pluginDescription: string;
  needPlugins:[];
  start:boolean;
  register:boolean;
}
