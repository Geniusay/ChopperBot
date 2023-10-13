import {Live} from "@/views/data/hot_live/LiveTypes";

export interface Module{
  tagId: string;
  tagName: string;
  lives: Live[];
}
