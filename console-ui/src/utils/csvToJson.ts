// 定义CSV格式的文本
const csvText = `site_id,category_id,category_name,category_name_cn,site_name,description,icon,href
1001,101,topPicks,热门推荐,IconFont,国内最著名的图标搜索及管理平台，300万个图标下载,https://image.uisdc.com/wp-content/uploads/2022/08/uisdc-nav-iconfont.png,https://www.iconfont.cn/
1002,101,topPicks,热门推荐,FreePik,知名设计素材站！PSD、矢量图、图库应有尽有,https://image.uisdc.com/wp-content/uploads/2020/06/nav-freepik.png,https://www.freepik.com/?ref=uisdc.com
1003,101,topPicks,热门推荐,每日灵感,优设旗下灵感频道，每日都有新灵感,https://image.uisdc.com/wp-content/uploads/2020/03/sdcnav-1-uu2.png,https://uiiiuiii.com/inspiration
1004,101,topPicks,热门推荐,摄图网,1000万+图片、插画、视频、模板，免费下载,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-1-4.png,http://699pic.com/?from=54
1005,101,topPicks,热门推荐,细节猎人,推荐！每日必看热门产品设计灵感，优设超人气频道,https://image.uisdc.com/wp-content/uploads/2022/07/nav-ui-hunter.png,https://www.uisdc.com/hunter
1006,101,topPicks,热门推荐,MasterGo设计神器,推荐！海量免费资源，更专业的云端UI设计工具,https://image.uisdc.com/wp-content/uploads/2022/02/sdcnav-1-mastergo-1.png,https://mastergo.com/?utm_source=youshe&utm_medium=toufang&utm_campaign=rementuijian&utm_term=&utm_content=
1007,101,topPicks,热门推荐,字由商用字体,推荐！近1000款免费商用字体一键拥有，设计师必备,https://image.uisdc.com/wp-content/uploads/2021/10/hellofont.png,https://www.hellofont.cn?from=UISDC:uisdc-w
1008,101,topPicks,热门推荐,优设读报,每日必读！设计新闻大事小事全知道,https://image.uisdc.com/wp-content/uploads/2020/03/nav-news-03192.png,https://www.uisdc.com/news
1009,101,topPicks,热门推荐,包图网,推荐！1000万套原创品质商用素材，效率神器,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-1-11.png,https://ibaotu.com/guanggao/1-0-0-0-3-1.html?spm=uisdc
1010,101,topPicks,热门推荐,Dribbble,国际知名设计站点！资深设计师必备,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-4-1.png,https://dribbble.com/
1011,101,topPicks,热门推荐,即时设计,推荐！免费专业级UI设计工具，可云端协作的Sketch,https://image.uisdc.com/wp-content/uploads/2021/06/nav-js-design.png,https://js.design/?source=uisdc&plan=rmtj
1012,101,topPicks,热门推荐,花瓣网,设计师寻找灵感的必备站点，启发设计灵感,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-8-2.png,https://huaban.com/
1013,102,hdGallery,高清图库,WallHaven,WallBase创办人失联后，前成员推出的网站,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-3-1.png,https://wallhaven.cc/
1014,102,hdGallery,高清图库,拍信海量创意图片,推荐！超1亿张全球精选潮流图片，有版权全网可商用,https://image.uisdc.com/wp-content/uploads/2020/09/paixin-ico-nav.png,https://www.paixin.com/?uisdc
1015,102,hdGallery,高清图库,Unsplash,知名免费图库，免费下载高分辨率照片，可商用,https://image.uisdc.com/wp-content/uploads/2022/11/sdcnav-unsplash-9.png,https://unsplash.com/
1016,102,hdGallery,高清图库,PNG素材库,推荐！最知名的PNG优质素材库。透明的、感动的,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-3-png.png,https://pngimg.com/
1017,102,hdGallery,高清图库,全画作,强烈推荐！几十万张超高分辨率艺术作品欣赏,https://image.uisdc.com/wp-content/uploads/2020/01/nav2020-qls.png,https://www.allhistory.com/painting
1018,102,hdGallery,高清图库,SplitShire,免费可商用的图片库，更有视频素材资源,https://image.uisdc.com/wp-content/uploads/2022/11/sdcnav-splitshire.png,https://www.splitshire.com/
1019,102,hdGallery,高清图库,Pexels,推荐！提供高清尺寸且品质优良的免费照片网站,https://image.uisdc.com/wp-content/uploads/2022/11/nav-pexels-2022.png,https://www.pexels.com/
1020,102,hdGallery,高清图库,微软Bing图库,推荐！帮你探索收集那些精美的图像，可按色彩检索,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-3-8.png,https://cn.bing.com/images/trending
1021,102,hdGallery,高清图库,Yestone创意图片库,每天3万张上新，全部正版商用授权，品质高到挑花眼,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-3-2.png,https://www.yestone.com/?ref=768164
1022,102,hdGallery,高清图库,StockSnap,推荐！专业的免费可商用照片图片素材资源库,https://image.uisdc.com/wp-content/uploads/2022/11/sdcnav-stocksnap-2022.png,https://stocksnap.io/
1023,102,hdGallery,高清图库,Pixabay,百万张免费高清图片，高质量可商用,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-3-12.png,https://pixabay.com/
1024,102,hdGallery,高清图库,500px,致力于视觉摄影分享、发现的专业平台,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-3-11.png,https://500px.com.cn/community/discover?t=fresh
1025,103,designTutorials,设计教程,优优教程网,超人气的优质中文教程网站，PS、AI、AE、C4D,https://image.uisdc.com/wp-content/uploads/2020/03/sdcnav-1-uu3.png,https://uiiiuiii.com/
1026,103,designTutorials,设计教程,PSDTuts+,Photoshop教程，从初学者到高级进阶，应有尽有,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-2-1.png,https://design.tutsplus.com/
1027,103,designTutorials,设计教程,Adobe 设计周报,Adobe官方站！大神们通过全家桶创作过程的经验总结,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-2-2.png,https://create.adobe.com/
1028,103,designTutorials,设计教程,PhotoshopVIP,日本超人气设计站点，网站很多干货,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-2-3.png,https://photoshopvip.net/
1029,103,designTutorials,设计教程,Smashing Magazine,国外最著名的设计博客，许多高大上的设计文章,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-2-4.png,https://www.smashingmagazine.com/
1030,103,designTutorials,设计教程,数字艺术在线,教程都很棒！你更会爱上网站展现教程的回廊方式,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-2-5.png,https://www.digitalartsonline.co.uk/tutorials/
1031,103,designTutorials,设计教程,优设网,与大师零距离接触，一线设计师、总监的干货分享地,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-2-7.png,https://www.uisdc.com/
1032,103,designTutorials,设计教程,Photoshop Lady,提供各种详细的photoshop教程，优设联盟站点,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-2-6.png,https://www.photoshoplady.com/
1033,103,designTutorials,设计教程,PSD爱好者,提供Photoshop教程、设计文章和资源下载,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-2-9.png,http://psd.fanextra.com/
1034,103,designTutorials,设计教程,站酷,综合性设计分享网站，原创设计交流平台,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-2-10.png,https://www.zcool.com.cn/
1035,103,designTutorials,设计教程,Abduzeedo,汇集大量视觉灵感和酷炫PS教程的设计博客,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-2-11.png,https://abduzeedo.com/
1036,103,designTutorials,设计教程,Coliss,日本有名站点！大量网站制作相关的设计技巧和神器,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-2-12.png,https://coliss.com/
1037,104,interfaceDesign,界面设计,Dribbble,设计师必备站点，国内顶尖的设计师都在上面,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-4-1.png,https://dribbble.com/
1038,104,interfaceDesign,界面设计,Behance,全球领先的创意设计类聚合平台,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-4-2.png,https://www.behance.net/
1039,104,interfaceDesign,界面设计,UI8,聚集世界各地优秀设计师的界面源文件,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-4-3.png,https://ui8.net/
1040,104,interfaceDesign,界面设计,Flat UI,扁平化UI设计灵感，采集扁平化相关的App、网页等,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-4-4.png,https://flatui.com/
1041,104,interfaceDesign,界面设计,UI Movement,展示世界最有才华设计师的界面动效设计作品,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-4-5.png,https://uimovement.com/
1042,104,interfaceDesign,界面设计,365psd,兢兢业业每天更新着用户界面相关的PSD,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-4-6.png,https://365psd.com/
1043,104,interfaceDesign,界面设计,CollectUI,UI设计必备！100多个分类，不用发愁没灵感了,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-4-7.png,https://collectui.com/
1044,104,interfaceDesign,界面设计,UI 设计,超赞！为您精挑细选的界面设计频道,https://image.uisdc.com/wp-content/uploads/2020/03/sdcnav-1-uu.png,https://uiiiuiii.com/inspirations/ui
1045,104,interfaceDesign,界面设计,Pixeden,赞！免费优质界面设计源文件及有网站模板,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-4-9.png,https://www.pixeden.com/
1046,104,interfaceDesign,界面设计,Site Inspire,致力于分享推荐优秀网页及交互设计案例,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-4-10.png,https://www.siteinspire.com/
1047,104,interfaceDesign,界面设计,Designmodo,所有高质量UI工具包都在这里可以找到，部分免费下载,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-4-11.png,https://designmodo.com/
1048,104,interfaceDesign,界面设计,UI 设计文章精选,推荐！前沿有料的UI设计文章在此集结,https://image.uisdc.com/wp-content/uploads/2022/07/nav-ui-2022.png,https://www.uisdc.com/category/uiicon
1049,105,ideas,灵感创意,Pinterest,一个受世界瞩目的，全球最大的创意灵感图片分享网站,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-8-1.png,https://www.pinterest.com/
1050,105,ideas,灵感创意,花瓣,采集你喜欢的美好事物，发现新知，启发设计灵感,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-8-2.png,https://huaban.com/
1051,105,ideas,灵感创意,UiiiUiii 灵感频道,推荐！超人气的灵感库，优优教程网旗下产品,https://image.uisdc.com/wp-content/uploads/2020/03/sdcnav-1-uu2.png,https://uiiiuiii.com/inspiration
1052,105,ideas,灵感创意,Designspiration,提供设计灵感、插画摄影、时尚以及艺术相关的一切,https://image.uisdc.com/wp-content/uploads/2020/02/designspiration-nav-2020.png,https://www.designspiration.com/
1053,105,ideas,灵感创意,Product Hunt,每天发现有趣的创新产品，离硅谷最近的眼睛,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-8-5.png,https://www.producthunt.com/
1054,105,ideas,灵感创意,幻觉,展示最惊人的创作，涵盖艺术、设计、摄影和视频,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-8-6.png,https://scene360.com/
1055,105,ideas,灵感创意,ArtStation,强烈推荐！一个收录世界顶级插画作品的社区,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-8-12.png,https://www.artstation.com/
1056,105,ideas,灵感创意,优设9图,顶尖设计知识卡片！每日必看灵感频道,https://image.uisdc.com/wp-content/uploads/2022/11/nav-group.png,https://www.uisdc.com/group
1057,105,ideas,灵感创意,BOOOOOOOM,发人深省的创意图片，博客设计简约但内容丰富,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-8-7.png,https://www.booooooom.com/
1058,105,ideas,灵感创意,灵感网络,推荐！聚合来自世界各地的创意人才，每天汲取灵感,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-8-8.png,https://theinspirationgrid.com/
1059,105,ideas,灵感创意,Lapa网页灵感图库,设计网站前，推荐来这里找灵感，有贴心全屏预览图,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-8-9.png,https://www.lapa.ninja/
1060,105,ideas,灵感创意,迷你单页狂热者,流行的网页趋势！不可不知的迷你站点大全,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-8-10.png,https://www.onepagemania.com/
1061,106,designTools,设计工具,神器推荐专栏,优设网神器推荐专栏，全球设计工具全收录,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-9-1.png,https://www.uisdc.com/category/hot-download/tools-download
1062,106,designTools,设计工具,MasterGo设计神器,推荐！海量免费资源，更专业的云端UI设计工具,https://image.uisdc.com/wp-content/uploads/2022/02/sdcnav-1-mastergo-1.png,https://mastergo.com?utm_source=youshe&utm_medium=wangzhidaohang&utm_campaign=shejigongju&utm_term=&utm_content=
1063,106,designTools,设计工具,阿里云建站神器,1034套PC+手机站网站模板！会打字就会建网站,https://image.uisdc.com/wp-content/uploads/2020/04/nav-aliyun-202018.png,https://ac.aliyun.com/application/webdesign/sumei?source=5176.11533457&userCode=itzns7ko
1064,106,designTools,设计工具,PPT神器 iSlide,一键优化！你和PPT高手之间就差这个插件了,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-9-3.png,https://www.uisdc.com/ppt-artifact-islide
1065,106,designTools,设计工具,京东JDR Design,推荐！京东设计团队的设计神器都在这里了,https://image.uisdc.com/wp-content/uploads/2020/03/jdr-design-nav.png,https://jdrd.jd.com/
1066,106,designTools,设计工具,Adobe全家桶,官方试用版下载链接，PS AI AE全都有，你懂得,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-9-6.png,https://uiiiuiii.com/software/5103.html
1067,106,designTools,设计工具,建站之星 SiteStar,国内著名建站品牌，PC+手机+小程序网站快速生成,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-9-7.png,https://www.sitestar.cn/
1068,106,designTools,设计工具,FocoClipping 在线抠图,强大的在线抠图神器！3秒出图全自动超方便,https://image.uisdc.com/wp-content/uploads/2021/08/nav-fococlipping.png,https://www.uisdc.com/fococlipping
1069,106,designTools,设计工具,H5制作神器,推荐！专业级H5制作神器，在线设计，无需编程,https://image.uisdc.com/wp-content/uploads/2019/11/nav-1107-epub.png,https://www.epub360.com/?utm_source=uisdc&utm_medium=ad&utm_campaign=hao
1070,106,designTools,设计工具,设计师神器,推荐！优设主编整理的设计师神器栏目,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-9-2.png,https://www.uisdc.com/category/tools-download
1071,106,designTools,设计工具,PS拉框助手,一款人气PS插件！可快速生成各类图表及UI控件,https://image.uisdc.com/wp-content/uploads/2019/10/pslkzs.png,https://www.pslkzs.com/
1072,106,designTools,设计工具,Vector Magic,1秒钟在线位图转矢量！好用的转矢量神器,https://image.uisdc.com/wp-content/uploads/2019/12/nav-vectormagic.png,https://vectormagic.com/
1073,107,ai,AI人工智能,AI绘画专题,推荐！为您精选AI绘画有关的神器和介绍,https://image.uisdc.com/wp-content/uploads/2022/11/nav-ai.png,https://www.uisdc.com/zt/ai-draw
1074,107,ai,AI人工智能,据意查句,清华出品！AI 神器让你的文案立马变高级,https://image.uisdc.com/wp-content/uploads/2022/11/nav-wantquotes.png,https://wantquotes.net/
1075,107,ai,AI人工智能,Img.Upscaler,一键变清晰！能将PNG/JPG图像分辨率升级2倍或4倍,https://image.uisdc.com/wp-content/uploads/2022/11/nav-imgupscaler.png,https://imgupscaler.com/
1076,107,ai,AI人工智能,EXPERTE,免费在线抠图工具，去除图像背景边缘清晰,https://image.uisdc.com/wp-content/uploads/2022/11/nav-experte.png,https://www.experte.com/background-remover
1077,107,ai,AI人工智能,ExtractBg,智能识别图像主体并一键去除背景，无下载限制,https://image.uisdc.com/wp-content/uploads/2022/11/nav-extractbg.png,https://extractbg.com/
1078,107,ai,AI人工智能,反向词典,人工智能同义词搜索工具，专治词语匮乏综合征,https://image.uisdc.com/wp-content/uploads/2022/11/nav-wantwords.png,https://wantwords.net/
1079,107,ai,AI人工智能,Get写作,全网热点追踪！一站式智能写作服务平台,https://image.uisdc.com/wp-content/uploads/2022/11/nav-getgetai.png,https://getgetai.com/
1080,107,ai,AI人工智能,Magic Eraser,一键抹去图片中不需要的元素，且毫无修图痕迹,https://image.uisdc.com/wp-content/uploads/2022/11/nav-magiceraser.png,https://www.magiceraser.io/
1081,107,ai,AI人工智能,Huemint,一键生成多种和谐配色方案！配色效果可实时预览,https://image.uisdc.com/wp-content/uploads/2022/11/nav-huemint.png,https://www.uisdc.com/huemint
1082,107,ai,AI人工智能,Relight,打光修图神器！呈现多姿多彩立体光影效果,https://image.uisdc.com/wp-content/uploads/2022/11/nav-relight.png,https://www.uisdc.com/relight
1083,107,ai,AI人工智能,Palette.fm,黑白图像转彩色！10秒内提供20种不同的上色效果,https://image.uisdc.com/wp-content/uploads/2022/11/nav-palettefm.png,https://www.uisdc.com/palette-fm
1084,107,ai,AI人工智能,NewProfilePic,免费的风格化头像生成器！让头像秒变美式插画风,https://image.uisdc.com/wp-content/uploads/2022/11/nav-NewProfilePic.png,https://www.uisdc.com/newprofilepic
1085,108,resources,素材资源,稿定设计,提供海量免费素材，多场景商业视觉在线设计平台,https://image.uisdc.com/wp-content/uploads/2018/12/sdcnav-gdsj-12.png,https://www.gaoding.com/utms/f10839263a60c1c447c75fcbe10183c2
1086,108,resources,素材资源,纹理王,免费的高分辨率顶尖纹理，可以商用,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-sc-1.png,https://www.textureking.com/category/all-textures/
1087,108,resources,素材资源,freebiesbug,最新的免费设计资源,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-sc-2.png,https://freebiesbug.com/
1088,108,resources,素材资源,LogoPond,高端logo集萃，设计前必须来池子里泡一泡,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-sc-3.png,https://logopond.com/
1089,108,resources,素材资源,阿里图标库,国内最著名的图标搜索及管理平台，300万个图标下载,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-1-1.png,https://www.iconfont.cn/
1090,108,resources,素材资源,矢量Logo下载,超赞！知名Logo矢量资源下载,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-sc-5.png,https://worldvectorlogo.com/
1091,109,fontDesign,字体设计,免费商用字体,超全！免费可商用中文+英文字体，含应用场景,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-10-1.png,https://uiiiuiii.com/tool/typeface
1092,109,fontDesign,字体设计,DaFont,国际知名站点！提供大量的免费英文字体下载,https://image.uisdc.com/wp-content/uploads/2020/08/sdcnav-font-28.png,https://www.dafont.com/
1093,109,fontDesign,字体设计,字体家,万款字体免费下载！专业提供正版授权字体下载,https://image.uisdc.com/wp-content/uploads/2022/02/sdcnav-font-ztj.png,https://www.zitijia.com/
1094,109,fontDesign,字体设计,字由,设计师必备字体利器，国内外上千款精选字体,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-10-4.png,https://www.hellofont.cn/
1095,109,fontDesign,字体设计,字体宇宙,超过6万免费字体下载，网站体验不错,https://image.uisdc.com/wp-content/uploads/2020/08/sdcnav-font-85.png,https://www.fontspace.com/
1096,109,fontDesign,字体设计,Font Fabric,免费高品质字体打包下载,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-10-6.png,https://www.fontfabric.com/
1097,109,fontDesign,字体设计,求字体,找字体神器，并提供中文和英文字体库下载,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-10-7.png,https://www.qiuziti.com/
1098,109,fontDesign,字体设计,字体天下,推荐！超过3万个中英文字体免费下载,https://image.uisdc.com/wp-content/uploads/2019/02/2019-nav-fontcn.png,https://www.fonts.net.cn/
1099,109,fontDesign,字体设计,云字库,最适合中小型设计团队的字体商用解决方案,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-10-3.png,https://www.yestone.com/fonts/?ref=768164
1100,109,fontDesign,字体设计,字体传奇,字体品牌设计师交流网，字体相关的教程、讲座等,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-10-10.png,http://www.ziticq.com/
1101,109,fontDesign,字体设计,Lost Type,推荐！字体都非常漂亮，展示方式悦目清新,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-10-11.png,http://www.losttype.com/browse/
1102,109,fontDesign,字体设计,字体松鼠,100%免费下载可商用！专为设计师精心挑选,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-10-12.png,https://www.fontsquirrel.com/
1103,110,template,酷站模版,Awwwards,精挑细选世界各地的最佳网站！并对其打分颁奖,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-7-1.png,https://www.awwwards.com/
1104,110,template,酷站模版,日本 4db,推荐！搜集日本各行各业、各地区的优秀网站,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-7-3.png,http://4db.cc/
1105,110,template,酷站模版,网页模版巨人,著名的网页模版库，借鉴和学习网页趋势的宝地,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-6-1.png,https://www.templatemonster.com/cn/
1106,110,template,酷站模版,酷站剪报,强烈推荐！用心收集日本酷站，右侧二级栏目非常棒,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-7-8.png,https://www.webdesignclip.com/
1107,110,template,酷站模版,迷你网站模版分享,推荐！提供海量单页模版展示和素材资源的网站,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-6-2.png,https://onepagelove.com/
1108,110,template,酷站模版,梦幻模板,超过7000个梦幻般的网站模板及Flash模板下载,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-6-5.png,http://www.dreamtemplate.com/
1109,110,template,酷站模版,优质登录页模版网,推荐！整合了各个行业的网站登录页面模版资源,https://image.uisdc.com/wp-content/uploads/2022/07/nav-web-2022.png,https://www.landingfolio.com/
1110,110,template,酷站模版,享誉全球的 WIX,超赞！该站全球排名378！可以帮你免费定制网站,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-6-7.png,https://www.wix.com/
1111,110,template,酷站模版,网页设计画廊,精选了富有创意的网页设计作品,https://image.uisdc.com/wp-content/uploads/2022/07/nav-io3000-2022.png,https://io3000.com/
1112,110,template,酷站模版,日本酷站索引,近6000个！av大合集也没这么全，酷站集合,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-7-12.png,http://bm.straightline.jp/
1113,111,colourScheme,配色方案,The FWA,强烈推荐！超高水准的互动网站设计案例,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-7-fwa.png,https://thefwa.com/
1114,111,colourScheme,配色方案,81 web,推荐！日本优秀出色的站点大集合,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-7-9.png,https://81-web.com/
1115,111,colourScheme,配色方案,Adobe Color,网页设计师配色的最佳之选,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-5-1.png,https://color.adobe.com/zh/
1116,111,colourScheme,配色方案,COLOURlovers,交流颜色、色彩趋势和配色方案的超人气社区,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-5-2.png,https://www.colourlovers.com/
1117,111,colourScheme,配色方案,Coolors,实用！成千上万的配色方案,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-5-3.png,https://coolors.co/browser/latest/1
1118,111,colourScheme,配色方案,漂亮的渐变颜色,今年流行的渐变！点击屏幕两侧按钮可选更多色彩,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-5-4.png,https://uigradients.com/
1119,111,colourScheme,配色方案,CssWinner网页色彩分类,CSS画廊，可根据右侧颜色块展现最流行的网页,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-5-5.png,https://www.csswinner.com/colorsearch/blue
1120,111,colourScheme,配色方案,色彩猎人,每天收集并策划发布美丽的配色方案,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-5-6.png,https://colorhunt.co/
1121,111,colourScheme,配色方案,中国色彩大辞典,中国/日本传统色彩命名，点击色彩可直接吸取色值,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-5-7.png,https://color.uisdc.com/
1122,111,colourScheme,配色方案,配色导航,推荐！流行配色方案，可一键复制喜欢的颜色,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-5-8.png,https://color.uisdc.com/pick.html
1123,111,colourScheme,配色方案,DopelyColors,一款实用的获取漂亮渐变方案的工具,https://image.uisdc.com/wp-content/uploads/2021/08/nav-colors-dopely.png,https://colors.dopely.top/
1124,111,colourScheme,配色方案,MaterialUI,推荐！帮助设计师们快速选到自己喜爱的配色方案,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-5-10.png,https://www.materialui.co/
1125,111,colourScheme,配色方案,ColorDrop,让寻找配色方案成为信手拈来的事情,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-5-11.png,https://colordrop.io/
1126,111,colourScheme,配色方案,Fashion Trendsetter,帮你关注每年最流行的颜色搭配,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-5-12.png,https://www.fashiontrendsetter.com/
1127,112,anime,二次元,B站,国内知名视频弹幕网站，有最及时的动漫新番,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-15-8.png,https://www.bilibili.com/
1128,112,anime,二次元,A站,AcFun作为弹幕视频网站，国内二次元文化的开创者,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-15-6.png,https://www.acfun.cn/
1129,112,anime,二次元,半次元,ACG爱好者社区,https://image.uisdc.com/wp-content/uploads/2020/01/nav2020-bcy.png,https://bcy.net/
1130,112,anime,二次元,腾讯动漫,中国最大最权威的正版动漫网站,https://image.uisdc.com/wp-content/uploads/2019/01/sdcnav-15-32.png,https://ac.qq.com/
1131,112,anime,二次元,动漫之家,国内最全最专业的在线漫画,https://image.uisdc.com/wp-content/uploads/2020/01/nav2020-dmzj.png,https://www.dmzj.com/
1132,112,anime,二次元,萌娘百科,万物皆可萌的百科全书,https://image.uisdc.com/wp-content/uploads/2020/01/nav2020-meng.png,https://zh.moegirl.org/Mainpage
1133,112,anime,二次元,有妖气,中国唯一且最大的纯原创漫画网站,https://image.uisdc.com/wp-content/uploads/2020/01/nav2020-yyq.png,https://www.u17.com/
1134,112,anime,二次元,不可能的世界,中国最大的二次元小说平台,https://image.uisdc.com/wp-content/uploads/2020/01/nav2020-bkn.png,https://www.8kana.com/
1135,112,anime,二次元,斗鱼,知名弹幕式直播平台,https://image.uisdc.com/wp-content/uploads/2020/01/nav2020-douyu.png,https://www.douyu.com/
1136,112,anime,二次元,虎牙,弹幕式互动直播平台,https://image.uisdc.com/wp-content/uploads/2020/01/nav2020-huya.png,https://www.huya.com/
1137,112,anime,二次元,Steam,全球综合性数字游戏社交平台，请勿沉迷,https://image.uisdc.com/wp-content/uploads/2020/01/nav2020-steam.png,https://store.steampowered.com/
1138,112,anime,二次元,P站,需爬梯！全球知名的插画作品分享站,https://image.uisdc.com/wp-content/uploads/2020/01/nav2020-pixiv.png,https://www.pixiv.net/
1139,113,photo,摄影美图,BURST,知名Shopify旗下摄影图库！每周更新优质照片素材,https://image.uisdc.com/wp-content/uploads/2022/02/sdcnav-12-burst.png,https://burst.shopify.com/
1140,113,photo,摄影美图,POCO摄影图片社区,领先的时尚摄影平台，分享作品和技巧首选,https://image.uisdc.com/wp-content/uploads/2020/02/poco-nav-2020.png,https://www.poco.cn/
1141,113,photo,摄影美图,新浪图片,有温度的视觉，摄影师成长平台，影像记录中国,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-14-3.png,http://photo.sina.com.cn/
1142,113,photo,摄影美图,蜂鸟网,摄影爱好者分享摄影技巧和作品的中国影像门户,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-14-4.png,https://www.fengniao.com/
1143,113,photo,摄影美图,中国国家地理网,最专业的深度旅游体验平台，最具特色的互动社区,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-14-5.png,http://www.dili360.com/
1144,113,photo,摄影美图,时尚网,美图看不完！高端时尚白领生活专属领地,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-14-6.png,http://www.trends.com.cn/
1145,113,photo,摄影美图,图虫网,图虫网，中国最专业的web2.0摄影社区,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-14-10.png,https://tuchong.com/
1146,113,photo,摄影美图,Style-Arena,日本街拍帅哥美女一览无余！东京街头时尚网,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-14-8.png,https://www.style-arena.jp/ch/
1147,113,photo,摄影美图,Chiphell,专业的发烧友社区，里面的摄影专区也不错,https://image.uisdc.com/wp-content/uploads/2021/01/sdcnav-chiphell.png,https://www.chiphell.com/portal.php?mod=list&catid=3
1148,113,photo,摄影美图,网易图片,网易新闻中心图片频道，24小时热图实时推送,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-14-7.png,http://news.163.com/photo
1149,113,photo,摄影美图,Photography Served,全球顶尖创意平台Behance旗下的摄影服务栏目,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-14-11.png,https://www.behance.net/galleries/Photography
1150,113,photo,摄影美图,无忌视界,国内外优秀摄影师的线上展览平台，开阔眼界,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-14-12.png,https://vision.xitek.com/sight/
1151,114,insight,行业视野,腾讯CDC,腾讯用户研究与体验设计中心，腾讯的核心部门之一,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-11-1-1.png,https://cdc.tencent.com/
1152,114,insight,行业视野,虎嗅网,一个有视角的、个性化商业资讯与交流平台,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-16-2-1.png,https://www.huxiu.com/
1153,114,insight,行业视野,36氪,互联网领先新商业媒体，让一部分人先看到未来,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-16-3-1.png,https://36kr.com/
1154,114,insight,行业视野,W3C官网,这是互联网的基础，推荐及时了解前端最新资讯,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-w3c-1.png,https://www.w3.org/
1155,114,insight,行业视野,极客公园,报道互联网热门趋势、热点产品的深度分析,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-16-5-1.png,http://www.geekpark.net/
1156,114,insight,行业视野,爱范儿,发现创新价值的科技媒体，全景关注移动互联网,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-16-6-1.png,https://www.ifanr.com/
1157,114,insight,行业视野,优设读报,极具影响力的设计师新闻频道，行业资讯一站知晓,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-16-7-1.png,https://www.uisdc.com/news
1158,114,insight,行业视野,cnBeta中文IT资讯站,提供最新最快的IT业界资讯,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-16-8-1.png,https://www.cnbeta.com/
1159,114,insight,行业视野,雷锋网,专注于移动互联网创业及创新的人气科技博客,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-16-9-1.png,https://www.leiphone.com/
1160,114,insight,行业视野,网易科技,有态度！以独特视角呈现科技圈内大事小事,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-16-10-1.png,https://tech.163.com/
1161,114,insight,行业视野,牛华网,华军软件园旗下网站，第一时间提供最具价值IT资讯,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-16-11-1.png,http://www.newhua.com/
1162,114,insight,行业视野,DoNews,中国互联网从业人士交流最权威的平台,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-16-12.png,https://www.donews.com/
1163,115,viedo,短视频,剪映,强悍的手机短视频制作神器！由抖音官方推出,https://image.uisdc.com/wp-content/uploads/2020/07/sdcnav-ulikecam.png,https://lv.ulikecam.com/
1164,115,viedo,短视频,万兴喵影,好用易上手的国产剪辑神器，功能强大,https://image.uisdc.com/wp-content/uploads/2020/07/sdcnav-wondershare.png,https://miao.wondershare.cn/
1165,115,viedo,短视频,蝉妈妈,推荐！为短视频内容创作团队提供竞品分析和爆款研究,https://image.uisdc.com/wp-content/uploads/2020/07/sdcnav-chanmama.png,https://www.chanmama.com/
1166,115,viedo,短视频,西瓜视频,字节跳动旗下视频平台！给你新鲜好看,https://image.uisdc.com/wp-content/uploads/2020/07/sdcnav-ixigua.png,https://www.ixigua.com/
1167,115,viedo,短视频,小红书,标记生活，一起分享和发现世界的精彩,https://image.uisdc.com/wp-content/uploads/2020/07/sdcnav-xiaohongshu.png,https://www.xiaohongshu.com/
1168,115,viedo,短视频,Videezy,推荐！提供大量免费高画质HD、4K影片素材,https://image.uisdc.com/wp-content/uploads/2020/07/sdcnav-videezy.png,https://www.videezy.com/
1169,115,viedo,短视频,Jamendo Music,质量很高的免费音乐素材下载平台,https://image.uisdc.com/wp-content/uploads/2020/07/sdcnav-jamendo.png,https://www.jamendo.com/start
1170,115,viedo,短视频,Bensound,独特有质感的免费背景音乐素材站点,https://image.uisdc.com/wp-content/uploads/2020/07/sdcnav-bensound.png,https://www.bensound.com/
1171,115,viedo,短视频,TooBigData,短视频网红排行，KOL一网打尽,https://image.uisdc.com/wp-content/uploads/2020/07/sdcnav-toobigdata.png,https://toobigdata.com/
1172,115,viedo,短视频,PremiumBeat,创意人士的短视频配乐佳选，有可商用音乐库,https://image.uisdc.com/wp-content/uploads/2020/07/sdcnav-premiumbeat.png,https://www.premiumbeat.com/zh/
1173,115,viedo,短视频,抖音,音乐创意短视频社交软件，记录美好生活,https://image.uisdc.com/wp-content/uploads/2020/07/sdcnav-douyin.png,https://www.douyin.com/
1174,115,viedo,短视频,快手,国民短视频社区，记录世界记录你,https://image.uisdc.com/wp-content/uploads/2020/07/sdcnav-kuaishou.png,https://www.kuaishou.com/
1175,116,designTraining,设计培训,优设大课堂,零基础课程，设计师涨薪就业职场直达,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-px-1.png,https://xue.uisdc.com/?c=train
1176,116,designTraining,设计培训,推荐！态爷手绘提高课,央视合作画师，绝无仅有的手绘课程,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-px-3.png,https://xue.uisdc.com/detail?id=5f365ff7dbd5ea3cf97cee73
1177,116,designTraining,设计培训,优设平面设计实战营,高薪第1步！大家都在学的平面设计必修课,https://image.uisdc.com/wp-content/uploads/2022/05/sdcnav-pm-9.png,https://uiiiuiii.com/other/1212116139.html
1178,116,designTraining,设计培训,零基础手绘就业班,超人气课程！设计师必备基础课，从入门到专业,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-px-3.png,https://xue.uisdc.com/detail?id=5d133b69d8071825ebce60c5&c=train-2
1179,116,designTraining,设计培训,优设自学好课,每周更新！好课风向标，学什么看这里,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-px-5.png,https://pro.uisdc.com/
1180,116,designTraining,设计培训,青爵版式设计全能班,爆款课程！站酷600W超人气青爵直播授课,https://image.uisdc.com/wp-content/uploads/2020/02/dkt-uisdc-200209.png,https://xue.uisdc.com/detail?id=5dcc1b4cddb4671468955397
1181,116,designTraining,设计培训,零基础UI设计就业班,优设官方培训课程，从基础到专业特训班,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-px-9.png,https://xue.uisdc.com/detail?id=5cb6ff4c0f29d0571d4fb7e4&c=train-7
1182,116,designTraining,设计培训,零基础C4D特训营,升职加薪筹码，让晋升之路畅通无阻,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-px-8.png,https://xue.uisdc.com/detail?id=5d133ad1d8071825ebce60c1&c=train-6
1183,116,designTraining,设计培训,设计师就业衔接班,60天从PS、AI、AE入门到设计入行！把握就业薪风向,https://image.uisdc.com/wp-content/uploads/2022/05/sdcnav-xj-92.png,https://uiiiuiii.com/other/1212116455.html
1184,116,designTraining,设计培训,优设手绘就业衔接班,6000人都选择的手绘课，助力升职加薪,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-px-3.png,https://uiiiuiii.com/other/1212113189.html
1185,116,designTraining,设计培训,动效设计特训营,火热报名！优设官方开展的动效设计视觉培训课程,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-px-10.png,https://xue.uisdc.com/detail?id=5cc55404bdc6da1b2b9c932e&c=train-8
1186,116,designTraining,设计培训,小光的手绘课堂,超人气课程！名师授课，商业手绘必学课程,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-px-12.png,https://xue.uisdc.com/detail?id=5cb700d30f29d0571d4fb7ea&c=train-5
1187,117,frontend,前端开发,w3school在线教程,必备！全球最大的中文Web技术教程,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-16-4-1.png,https://www.w3school.com.cn/
1188,117,frontend,前端开发,凹凸实验室,推荐！沉淀与分享前端开发、页面制作技巧,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-13-4.png,https://aotu.io/
1189,117,frontend,前端开发,代码笔,超赞！面向前端设计人员的圣地,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-13-2.png,https://codepen.io/
1190,117,frontend,前端开发,w3ctech,推荐！中国最大的前端技术社区,https://image.uisdc.com/wp-content/uploads/2018/09/2018-w3ctech-0906.png,https://www.w3ctech.com/
1191,117,frontend,前端开发,4分钟网页,经典教学案例！教你4分钟变戏法式制作一个网页,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-13-10.png,https://hao.uisdc.com/cssmagic/
1192,117,frontend,前端开发,Bootstrap中文网,简洁、直观、强悍的响应式前端开发框架,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-b-12.png,https://www.bootcss.com/
1193,118,InteriorDesign,室内设计,Houzz,全球最著名的装修和室内设计平台,https://image.uisdc.com/wp-content/uploads/2018/09/nav-architecture-1.png,https://www.houzz.com
1194,118,InteriorDesign,室内设计,AD,AD在室内设计领域具有不可估量的影响力,https://image.uisdc.com/wp-content/uploads/2018/09/nav-architecture-5.png,https://www.architecturaldigest.com/
1195,118,InteriorDesign,室内设计,躺平设计家,国内一流的室内设计平台,https://image.uisdc.com/wp-content/uploads/2018/09/nav-architecture-3.png,https://www.shejijia.com/
1196,118,InteriorDesign,室内设计,美国家园频道,致力于家庭装修、园艺、手工艺和家庭改造方案,https://image.uisdc.com/wp-content/uploads/2018/09/nav-architecture-2.png,https://www.hgtv.com/
1197,118,InteriorDesign,室内设计,ArchDaily,世界最受欢迎的建筑网站,https://image.uisdc.com/wp-content/uploads/2018/09/nav-architecture-6.png,https://www.archdaily.com
1198,118,InteriorDesign,室内设计,设计牛奶,一本致力于现代设计的在线灵感杂志,https://image.uisdc.com/wp-content/uploads/2018/09/nav-architecture-4.png,https://design-milk.com/
1199,119,designMedia,设计媒体,优设,这是我们优设的官方品牌微博，强烈推荐,https://image.uisdc.com/wp-content/uploads/2020/02/nav-yes-0209.png,https://weibo.com/uisdc2012
1200,119,designMedia,设计媒体,优设基础训练营,每日推荐优质Ps Ai Ae教程，优优网官博,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-19-4.png,https://weibo.com/uisdcjichuying
1201,119,designMedia,设计媒体,优优教程网,优设旗下网站，优优教程网官方微博,https://image.uisdc.com/wp-content/uploads/2020/03/sdcnav-1-uu3.png,https://weibo.com/uiiiuiii
1202,119,designMedia,设计媒体,设计师的百宝箱,精挑细选受设计师欢迎的网站，都是奇珍异宝,https://image.uisdc.com/wp-content/uploads/2022/04/sdcnav-design-box.png,https://weibo.com/edddesign
1203,119,designMedia,设计媒体,你丫才美工,严格意义来讲，打水印和打码一样，都是下流的凑性,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-19-1.png,https://weibo.com/honghaier555
1204,119,designMedia,设计媒体,意匠id,最精致的微博拥有最好的粉丝，分享美好，分享生活,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-19-8.png,https://weibo.com/mdabao
1205,119,designMedia,设计媒体,优秀网页设计,设计干货微博！每日更新及时，推荐关注,https://image.uisdc.com/wp-content/uploads/2020/02/nav-uisdc-0209.png,https://weibo.com/uidesign
1206,119,designMedia,设计媒体,优设大课堂,跟对老师学知识，优设大课堂官方微博,https://image.uisdc.com/wp-content/uploads/2020/02/nav-dkt-0209.png,https://weibo.com/u/6021844857
1207,119,designMedia,设计媒体,平面版式设计,知名设计美学博主，前沿的平面版式设计灵感,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-px-4.png,https://weibo.com/u/5317715201
1208,119,designMedia,设计媒体,手绘插画设计,知名设计美学博主,https://image.uisdc.com/wp-content/uploads/2018/08/sdcnav-px-3.png,https://weibo.com/u/3123287587
1209,119,designMedia,设计媒体,Photoshop大师,微博知名设计美学博主,https://image.uisdc.com/wp-content/uploads/2022/11/sdcnav-ps-2022.png,https://weibo.com/psdashi
1210,119,designMedia,设计媒体,招聘设计师,可以帮你找工作的微博，准备好简历@她即可,https://image.uisdc.com/wp-content/uploads/2020/02/nav-job-0209.png,https://weibo.com/desginjob
`;

// 使用split方法将文本按行分割
const lines = csvText.split("\n");

// 定义JSON对象的键名数组
const keys = lines[0].split(",");

// 使用map方法将其余行转换为JSON对象
const json = lines.slice(1).map((line) => {
  // 将行按逗号分割
  const values = line.split(",");
  // 返回一个对象，其键名对应keys数组中的值，键值对应values数组中的值
  return keys.reduce((obj, key, i) => ({ ...obj, [key]: values[i] }), {});
});

// 输出结果
console.log(json);
