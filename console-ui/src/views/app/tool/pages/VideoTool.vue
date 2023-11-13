<template>
  <div ref="VideoTool" class="VideoTool">
    <el-upload
      :before-upload="beforeUpload"
      :file-list="fileList"
      :on-change="handleChange"
      :on-remove="handleRemove"
      action=""
      class="upload"
      drag
      multiple>
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    </el-upload>
    <div class="SelectContainer">
      <div class="select">
        <el-select v-model="CurrentType">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <span style="margin:0 20px 0 20px;display: inline-block;font-family: 'Hiragino Sans GB';font-weight: 700;line-height: 32px">
          转成
        </span>
        <el-select v-model="TargetType">
          <el-option
            v-for="item in options2"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </div>
      <div class="button" @click="transform">
        <el-button style="width: 80px;position: relative;background-color: rgb(255,44,85);color: white">开始转换</el-button>
      </div>
      <p ref="messageRef"></p>
      <video ref="video" controls>

      </video>
    </div>

  </div>
</template>

<script>
import { FFmpeg } from '@ffmpeg/ffmpeg';
import {fetchFile, toBlobURL} from '@ffmpeg/util';
let ffmpegRef = new FFmpeg()
let ffmpeg = ffmpegRef;
export default {
  name: "VideoTool",
  data () {
    return {
      ffmpeg:[],
      options:[
        {
          label: 'flv',
          value: 'flv'
        },
        {
          label: 'm3u8',
          value: 'm3u8'
        },
        {
          label: 'webm',
          value: 'webm'
        },
        {
          label: 'avi',
          value: 'avi'
        },
        {
          label: 'mp4',
          value: 'mp4'
        }
      ],
      options2:[
        {
          label: 'flv',
          value: 'flv'
        },
        {
          label: 'm3u8',
          value: 'm3u8'
        },
        {
          label: 'webm',
          value: 'webm'
        },
        {
          label: 'avi',
          value: 'avi'
        },
        {
          label: 'mp4',
          value: 'mp4'
        }
      ],
      CurrentType: '',
      TargetType: '',
      file: null,
      fileList: [
      ]
    }
  },
  methods: {
    beforeUpload(file){
      this.file = file
      return false
    },
    handleChange(file, fileList) {
      this.fileList = [{name:file.name}];
    },
    handleRemove(file){
      this.fileList = []
      this.file = null
    },
    async transform() {
      if(!this.file){
        this.$message.warning("请选择文件")
        return
      }
      if(!this.CurrentType||!this.TargetType){
        this.$message.warning("请选择格式")
        return
      }
      if(!this.CurrentType === this.TargetType){
        this.$message.warning("格式相同")
        return
      }
      let file = this.file
      let that = this
      let reader = new FileReader()
      reader.addEventListener(
        "load",
        function () {
          (async () => {
            await ffmpeg.writeFile(`input.${that.CurrentType}`,await fetchFile(reader.result));
            await ffmpeg.exec(['-i', `input.${that.CurrentType}`,'-c','copy', `output.${that.TargetType}`]);
            const data = await ffmpeg.readFile(`output.${that.TargetType}`);
            let downloadFile =new Blob([data.buffer], {type: `video/${that.TargetType}`})
            const downloadA = document.createElement("a");
            downloadA.href = URL.createObjectURL(downloadFile);
            downloadA.download = `output.${that.TargetType}`
            document.body.appendChild(downloadA);
            downloadA.click()
            document.body.removeChild(downloadA);
            that.$refs.video.setAttribute('src',URL.createObjectURL(new Blob([data.buffer], {type:  `video/${that.TargetType}`})))
          })()
        },
        false,
      );

      if (file) {
        reader.readAsDataURL(file);
      }
    },
    init(){
      let that = this;
      (async () => {
        let messageRef = that.$refs.messageRef
        ffmpeg.on('progress', ({ progress, time }) => {
          messageRef.innerHTML = `${(progress * 100).toFixed(5)} % `;
        });
        // toBlobURL is used to bypass CORS issue, urls with the same
        // domain can be used directly.
        const baseURL = '/ffmpeg-core'
        await ffmpeg.load({
          coreURL: await toBlobURL(`${baseURL}/ffmpeg-core.js`, 'text/javascript'),
          wasmURL: await toBlobURL(`${baseURL}/ffmpeg-core.wasm`, 'application/wasm'),
          workerURL: await toBlobURL(`${baseURL}/ffmpeg-core.worker.js`, 'text/javascript'),
        });
      })()
    }
  },
  mounted() {
    this.init()
  }
}
</script>

<style lang="scss" scoped>
  .VideoTool{
    position: relative;
    height: 100%;
    width: 100%;
    .upload{
      margin-top: 20px;
      position: relative;
      width: 80%;
      left: 50%;
      transform: translate(-50%);
    }
    .SelectContainer{
      margin-top: 20px;
      position: relative;
      width: 100%;
      height: 100px;
      .select{
        display: flex;
        position: relative;
        width: 500px;
        left: 50%;
        transform: translate(-50%);
      }
      .button{
        margin-top: 10px;
        position: relative;
        display: flex;
        width: 110px;
        left: 50%;
        transform: translate(-50%)
      }
    }
  }
</style>
