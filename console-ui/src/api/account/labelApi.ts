import request from "@/utils/request";
import { Label } from "@/views/app/label/LabelTypes";
export function list() {
  return request({
    url: "/account/label/list/",
    method: "get",
  });
}

export function add(label: string) {
  return request.post("/account/label/add/", { label: label });
}

export function remove(label: string) {
  return request({
    url: "/account/label/delete/",
    method: "get",
    params: {
      label: label,
    },
  });
}

export function update(Label: Label) {
  return request.post("/account/label/update/", Label);
}
