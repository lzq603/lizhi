/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50710
Source Host           : 127.0.0.1:3306
Source Database       : zhuanzhuans

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2018-08-25 19:51:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `userId` int(11) NOT NULL,
  `goodId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`goodId`),
  KEY `goodId` (`goodId`),
  CONSTRAINT `collect_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `collect_ibfk_2` FOREIGN KEY (`goodId`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('100001', '900007');
INSERT INTO `collect` VALUES ('100003', '900007');
INSERT INTO `collect` VALUES ('100006', '900007');
INSERT INTO `collect` VALUES ('100001', '900033');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('1', '哈哈', '联系方式', '2018-08-25 19:04:45');
INSERT INTO `feedback` VALUES ('2', 'sadlfjsaldfjlsadjf', 'sjdfalasjdf', '2018-08-25 19:05:54');
INSERT INTO `feedback` VALUES ('3', 'sadfsdf', '1111', '2018-08-25 19:07:53');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodname` varchar(255) NOT NULL,
  `goodownerid` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `num` int(10) NOT NULL,
  `catagory` varchar(25) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `goodowner` (`goodownerid`),
  CONSTRAINT `goodownerid` FOREIGN KEY (`goodownerid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=900044 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('900001', '联想ThinkPad USB Type-C移动扩展坞4X90M60793DELL小米HP华为 ', '100001', '380.00', 'Think品质保证，USB TYPE-C通用接口，支持华为，小米，HP，DELL等众多型号笔记本电脑（需要机器有USB TYPE-C接口），小巧便携，功能强大', 'TB23zQanFXXXXXLXXXXXX!!8.jpg;', '3', '数码产品;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900003', 'Sony/索尼 l55t /z3 移动4Gz3/D6653联通4G SOL26 三网电信联通4G', '100005', '570.00', 'L55T行货单卡 支持移动2G3G4G网络 /支持联通2G.网络.（拍亚太） D6653港版单卡 支持联通2G3G4G网络/移动2G 网络.（拍港澳台） SOL26日版单卡支持联通2G3G4G网络/移动2G网络/电信3G.网络.（拍日本）', 'TB23zQanFXXXXXLXXXXXXXXXXXX_!!851172226.jpg;', '1', '数码产品;手机;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900004', '乌木', '100001', '199.00', '20cm盆，超大，三年前从云南斗南背回来的，喜欢的朋友私聊！', 'TB2_tqPhTnI8KJj.jpg;TB2nFCrhInI8KJjSsz.jpg;TB2yNWBhJ.jpg;', '1', '园艺植物;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900007', '秋冬季阿迪NMD圣保罗女鞋透气爆米花东京男鞋运动情侣款跑步潮鞋', '100005', '98.00', '码数36-44码标准码感兴趣的留言或点我想要的直接付款，喜欢的拍下付款然后码数,我会第一时间内给你安排发货。需要其他颜色留言备注就可以，或聊天我。附带专柜正品小票要小票留言。询问其他款式可以联系微客服：qa1174官方正品,原装包装,支持7天退换,货到付款,全部低价销售 一手的货 二手的特价 全部现货当天拍下 当天发货 如人不在未回复 请放心直接购买 我会为你安排好一切', 'TB1yFlUPpXXXXc.jpg;TB205wnaCqJ.eB.jpg;TB2XSfKaOGO.eBjS.jpg;TB2GjvHaRyN.eBjS.jpg;', '1', '服饰;鞋', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900008', '联想笔记本', '100005', '1399.00', '联想100s 红色外观，双核处理器4G内存128固态硬盘集成显卡，外观完好，', 'TB2Pe6ug9YH8KJj.jpg;TB2eT1EcpHM8KJj.jpg;TB2SkuKbPgy_.jpg;', '1', '数码产品;笔记本电脑;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900009', '长城傻瓜相机闲置#胶卷机#', '100005', '170.00', '十月份乐玩公社全新购入，拍了两卷。图一是评论底下拿的图...我太懒了。图二三是我自己拍的，刚冲出，感觉来还不错。好用无磕碰。自动闪光不可关。170包邮！赠一卷富士c200.不退换', 'TB2FHDqb2jM.jpg;TB29Avjb3jN8KJjS.jpg;TB29Avjb3jN8KJjSZFgX.jpg;', '1', '数码产品;相机;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900010', '卡通u盘', '100001', '30.00', '一号店限量卡通u盘，七周年纪念款，全新未开封，8g内存，读写速度快！一经售出不退不换，谢谢支持！', 'TB2UygFdVXXX.jpg;TB25UQxdVXXXXbNXX.jpg;', '1', '数码产品;U盘;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900011', '全新安塞尔斯春秋款长袖纯棉套装', '100001', '75.00', '安塞尔斯正品，尺码如下所示。图一，130码，89元图二，110,120,130码，89元图三，110码，89元图四，120码，89元以上都有吊牌。注意⚠️，另外图二蓝色小熊款有少量全新无吊牌款，90码，100码都是全新，就是没有吊牌，水洗标和小熊图标都有的。特价75元一套。只有图二款。', 'TB2jodreI2vU1JjS.jpg;TB2kGsheGmgSKJjS.jpg;TB2qsXZiYsTMeJj.jpg;TB2OvmAcEMgYe.jpg;', '1', '服饰;春秋款长袖纯棉套装;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900012', '扫地机器人', '100001', '550.00', '美国KOBOT科擘 智能家用扫地机器人 吸尘器rv317。经典，全新未拆封。', 'n_v24e52703b4b1a4e.jpg;n_v20bfc79ffa1eb4.jpg;n_v2ea8152a21d584.jpg;', '1', '居家日用;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900013', '凤凰山地自行车240', '100001', '240.00', '八成新，诚心校内可以送', 'TB26c0odBLN8K.jpg;', '2', '运动户外;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900014', '电动刮胡刀', '100001', '48.00', '全新HTC电动刮胡刀 请走转转担保交易，喜欢的话就赶快联系我吧。', 'n_v27cdab203ec3.jpg;n_v2bd80ae2.jpg;n_v287188565038.jpg;', '2', '居家日用;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900015', '儿童读物隐藏的图画系列', '100001', '30.00', '今天(12月21日)刚寄到，儿子不喜欢，因为他觉得这是公主系列，30元入手的，30元包邮出，两套书，1套5本，一套4本(很厚)，10大张公主贴纸，一盒蜡笔，偏远不发！', 'n_v232d524a59.jpg;n_v2e928753.jpg;n_v2d5f4542.jpg;', '1', '图书音像;儿童读物', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900016', '16本，打包了，宝贝们！', '100001', '200.00', '（其中7本全新塑封！）', 'n_v22f4cb02.jpg;n_v2a0fe25baa.jpg;n_v2147d4c0fba874.jpg;', '1', '图书音像;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900017', '佳能相机包', '100001', '5.00', '闲置相机包\r\n入手渠道：专卖店\r\n转手原因：闲置\r\n规格尺寸：入门机使用\r\n新旧程度：97', 'n_v25a1638164.jpg;n_v2895a89cb.jpg;n_v2b6d440.jpg;', '1', '运动户外;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900018', '飞智游戏手柄', '100001', '95.00', '飞智游戏手柄，拉伸式蓝牙4.0安卓 苹果通用黑色，不想要了，便宜卖500毫安电池低耗电，可验货\r\n新旧程度：95 可以去京东上搜索觉得真实', 'n_v21acd7bf47f4.jpg;n_v2e03de553d.jpg;n_v272397500c67.jpg;', '1', '游戏设备;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900019', '全新usb供电小音箱', '100001', '15.00', '不知道老婆何时买了个这个，而且比较久了，一直没用，盒子略旧。。', 'n_v1bl2lwkc.jpg;n_v1bl2lwx.jpg;n_v1bkujjd.jpg;', '1', '数码产品;音响;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900020', '红米3闲置！买了一年多！不用了！85成新', '100005', '288.88', '转手低价！郑州面交！先到先得！账号的密码忘记了！无法恢复出厂设置！我也没办法啊！银色2+16！限面交！不议价！无拆机无维修！屏幕完好！爱手机的人OK！原价买的699', 'TB29B99hv2H8KJj.jpg;TB2caYBhv2H8KJjy1z.jpg;', '1', '数码产品;手机;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900021', '飞利浦 Philips CD机 收音机 FM 可读MP3 WMA盘 学习 胎教使用', '100005', '126.00', '机器功能全部正常，读盘效果好，正版刻录都读的很快。不包读天下盘。带收音机功能，并且效果非常棒，可以读MP3 WMA格式的光盘 ，带有FM收音机功能，非常实用机器的成色见图片，正面看着磨损比较严重，实际是这款机器的设计就有问题不耐磨损！！！打开里面比较新的配置:主机一台SonyE706耳机4.5v电源偏远不发，售出不退！', 'TB2xkKoXk.Oyu.jpg;TB2qaBxXhiEJuJjS.jpg;TB2FramXeIPyuJjS.jpg;', '1', '数码产品;MP3;CD机;收音机;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900022', '诺基亚C7', '100005', '130.00', '成色非常靓，很新，功能一切正常。原版机子电池', 'TB2CNe8iwn.jpg;TB2eUmKitz8.jpg;TB2Xf2uiv2H8KJ.jpg;', '1', '数码产品;手机;诺基亚;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900023', '世界经典名著注音版（全四册）法布尔昆虫记正版 亚米契斯爱的教', '100005', '23.00', '世界经典名著注音版（全四册）注音彩绘本，昆虫记，爱的教育，爱丽丝漫游奇境记，福尔摩斯探案集，中小学语文新课标必读丛书\r\n全新23元包邮，转转担保交易1229', 'n_v2dbc1be9a.jpg;n_v2b783e4f7.jpg;n_v2205a4c076.jpg;', '1', '图书音像;名著;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900024', '低价20转让两本励志女性畅销书籍心理学文化修养情绪必读书', '100005', '20.00', '全新正版， 不生气的智慧 +不抱怨的人生 ，心态人际关系沟通为人女性心理学气质文化修养调整情绪， 青春励志书籍心灵鸡汤 畅销书，自己买了一套读了还不错，这套是朋友赠送，低价转让，送给爱读书的你，包邮哦', 'n_v271afc4f8.jpg;n_v203939f9ff5.jpg;n_v26c3b19e5.jpg;', '1', '图书音像;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900025', '写生设计～中央美术学院设计学院基础部学生课程教学 正版包邮', '100005', '18.00', '9成新书。这是一本很有创意的写生教材，通过每年中央美院学生春秋两季的实践写生的精选作品给予读者创作思路的扩展和启发，这些作品形式上分为色彩和速写，但丰富的手法和广泛的审美性一定会带领读者融入其中，收益非凡。更难得的是还附送教师教学日记，更令人身临其境。总之，是一本难得的艺术类创作好书，是美院学生和自学爱好者的必备佳作。本书由山西人民出版社出版，包邮仅需18元，保证正版超值！', 'n_v20f519e45e.jpg;n_v28fed28dbfa.jpg;n_v2271e41.jpg;', '1', '图书音像;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900026', '四大名著 畅销书 共4册', '100005', '35.00', '【赠人物关系表】 中国四大名著全套青少年版红楼梦中小学生版水浒传西游记三国演义青少年版原著正版白话文儿童畅销书排行榜书籍', 'n_v20e0a5c4.jpg;n_v2a5a9932.jpg;n_v27e8c0f74.jpg;', '1', '图书音像;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900027', '钱币图库书一套3本，包邮', '100005', '40.00', '钱币图库书一套3本，包邮\r\n收藏用品：中国古钱图录，中国纸币图录，中国铜币图库，\r\n\r\n里面详细介绍中国各年代钱币知识和市场参考价！收藏不错的工具书！\r\n实物拍摄如图。\r\n快递10元。全国除：青，藏，疆', 'n_v2b1810d51.jpg;n_v215c3382.jpg;n_v2149bff.jpg;', '1', '图书音像;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900028', '世界未解之谜全套(8本)', '100005', '28.00', '全新 包邮 世界未解之谜8册小学生课外阅读书籍注音版一年级二年级必读三四老师班主任推荐带拼音的科普百科故事书6-7-8-12周岁1-2-3儿童读物(活动截止日期12月26号)', 'n_v2d9c336bd7.jpg;n_v22e1d2.jpg;n_v261e54f97.jpg;', '8', '图书音像;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900029', '山香教师资格证书，卷子 包邮', '100005', '40.00', '山香教师资格证书，综合素质基本全新，教育教学画了重点，卷子做了一大半。17年12月份考试。两课都一次性通过。\r\n附加：自己搜索的关于教师资格证的视频解析，每一课的详细讲解（由多种版本，可根据自己需要选择合适的）还有pdf版真题和难点讲解。还有面试通过技巧，希望考教师资格证的大家，都能一次性通过。\r\n不讲价！都是辛辛苦苦找的视频，从山香直接购买是没有的，视频是自己找的。', 'n_v287f7748.jpg;n_v28be0e59b5986.jpg;n_v220f1221.jpg;', '1', '图书音像;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900030', '世界经典名著注音版（全四册）法布尔昆虫记正版 亚米契斯爱的教', '100005', '23.00', '世界经典名著注音版（全四册）注音彩绘本，昆虫记，爱的教育，爱丽丝漫游奇境记，福尔摩斯探案集，中小学语文新课标必读丛书\r\n全新23元包邮，转转担保交易1229', 'n_v2dbc1be9a.jpg;n_v2b783e4f7.jpg;n_v2205a4c076.jpg;', '1', '图书音像;名著;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900031', '四大名著 畅销书 共4册', '100005', '35.00', '【赠人物关系表】 中国四大名著全套青少年版红楼梦中小学生版水浒传西游记三国演义青少年版原著正版白话文儿童畅销书排行榜书籍', 'n_v20e0a5c4.jpg;n_v2a5a9932.jpg;n_v27e8c0f74.jpg;', '1', '图书音像;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900032', 'Sony/索尼 l55t /z3 移动4Gz3/D6653联通4G SOL26 三网电信联通4G', '100005', '570.00', 'L55T行货单卡 支持移动2G3G4G网络 /支持联通2G.网络.（拍亚太） D6653港版单卡 支持联通2G3G4G网络/移动2G 网络.（拍港澳台） SOL26日版单卡支持联通2G3G4G网络/移动2G网络/电信3G.网络.（拍日本）', 'TB23zQanFXXXXXLXXXXXXXXXXXX_!!851172226.jpg;', '2', '数码产品;手机;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900033', '笔记本 文具 记事本 办公用品 学生日记本 全新', '100005', '5.00', '笔记本 文具 记事本 办公用品 学生日记本 全新，本人闲置新的各种笔记本 日记本 小便签本，大的10元，中的5元，小的3元，不包邮，喜欢的联系我吧！！', 'n_v29779f65.jpg;n_v27298aa.jpg;n_v21aa2d.jpg;', '1', '学习办公;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900034', '得力笔筒创意时尚圆形笔筒简约办公室学生桌面文具收纳黑色笔筒', '100005', '6.00', '得力笔筒创意时尚圆形笔筒简约办公室学生桌面文具收纳黑色笔筒', 'n_v2ba0108215.jpg;n_v228442f729.jpg;n_v2f8483e27.jpg;', '1', '学习办公;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900035', '儿童小学生智能写不断铅笔全自动2B铅笔文具礼品套装', '100005', '25.00', '儿童小学生智能写不断铅笔全自动2B铅笔文具礼品套装', 'n_v225716a2.jpg;n_v2c19b7e.jpg;n_v2cf59e4.jpg;', '1', '学习办公;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900036', '订书针', '100005', '6.00', '红丹阳订书针，用了半箱，剩下的便宜处理了。一大盒(10小盒)的价格。', 'n_v1bj3gz2.jpg;n_v1bkuyfvm.jpg;n_v1bkuy.jpg;', '1', '学习办公;', '2018-04-16 15:49:52');
INSERT INTO `goods` VALUES ('900037', '发声计算器', '100005', '9.00', '计算器 9块钱一个，一共8个，\r\n请走转转担保交易，喜欢的话就赶快联系我吧。', 'n_v1bl2lwwpd.jpg;n_v1bl2lwt.jpg;', '8', '学习办公;', '2018-04-16 15:49:52');

-- ----------------------------
-- Table structure for leave_msg
-- ----------------------------
DROP TABLE IF EXISTS `leave_msg`;
CREATE TABLE `leave_msg` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `senduserid` int(10) NOT NULL,
  `reciveuserid` int(10) NOT NULL,
  `goodid` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `reply` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sendid` (`senduserid`),
  KEY `reciveid` (`reciveuserid`),
  KEY `good` (`goodid`),
  KEY `reply` (`reply`),
  CONSTRAINT `leave_msg_ibfk_1` FOREIGN KEY (`goodid`) REFERENCES `goods` (`id`),
  CONSTRAINT `leave_msg_ibfk_2` FOREIGN KEY (`reciveuserid`) REFERENCES `users` (`id`),
  CONSTRAINT `leave_msg_ibfk_3` FOREIGN KEY (`senduserid`) REFERENCES `users` (`id`),
  CONSTRAINT `leave_msg_ibfk_4` FOREIGN KEY (`reply`) REFERENCES `leave_msg` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leave_msg
-- ----------------------------
INSERT INTO `leave_msg` VALUES ('0', '100000', '100000', '900001', '（附加记录）', '2018-04-25 16:10:07', '0');
INSERT INTO `leave_msg` VALUES ('21', '100001', '100001', '900004', '可以便宜点吗？', '2018-06-03 23:35:42', '0');
INSERT INTO `leave_msg` VALUES ('22', '100001', '100005', '900001', '456', '2018-06-09 12:18:15', '0');
INSERT INTO `leave_msg` VALUES ('26', '100000', '100000', '900004', '1', '2018-06-30 19:27:45', '0');
INSERT INTO `leave_msg` VALUES ('28', '100001', '100001', '900010', '嘻嘻嘻', '2018-06-30 19:36:55', '0');
INSERT INTO `leave_msg` VALUES ('29', '100001', '100005', '900001', '留了个言', '2018-07-01 16:32:47', '0');
INSERT INTO `leave_msg` VALUES ('30', '100001', '100005', '900001', '123', '2018-07-01 16:37:49', '0');
INSERT INTO `leave_msg` VALUES ('38', '100006', '100005', '900030', '受打击啊发', '2018-08-25 17:20:28', '0');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `senduserid` int(10) NOT NULL,
  `reciveuserid` int(10) NOT NULL,
  `goodid` int(11) NOT NULL,
  `status` int(2) NOT NULL,
  `content` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sendid` (`senduserid`),
  KEY `reciveid` (`reciveuserid`),
  KEY `good` (`goodid`),
  CONSTRAINT `good` FOREIGN KEY (`goodid`) REFERENCES `goods` (`id`),
  CONSTRAINT `receive` FOREIGN KEY (`reciveuserid`) REFERENCES `users` (`id`),
  CONSTRAINT `send` FOREIGN KEY (`senduserid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '100000', '100001', '900001', '2', '您好，欢迎加入转赚', '2018-04-21 23:02:15', '通知消息');
INSERT INTO `message` VALUES ('14', '100001', '100005', '900003', '0', '哈喽丽丽', '2018-06-11 23:29:27', '用户消息');
INSERT INTO `message` VALUES ('15', '100001', '100005', '900021', '0', '丽丽你好', '2018-06-11 23:29:55', '用户消息');
INSERT INTO `message` VALUES ('16', '100005', '100001', '900021', '0', '你好阿强', '2018-06-11 23:30:42', '用户消息');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(8) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `head` varchar(255) NOT NULL,
  `mobilephone` varchar(11) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `sex` int(1) NOT NULL COMMENT '1男 0女',
  `college` varchar(255) NOT NULL,
  `openid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100007 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('100000', '10000001', '系统', 'http://localhost/Zhuanzhuan/img/header/kefu.png', '10086', '999', '0', '石家庄铁道大学', '');
INSERT INTO `users` VALUES ('100001', '20163594', '小小小', 'https://thirdwx.qlogo.cn/mmopen/vi_32/1o8gB5DOdHfP8SLiaBNcWxIYG88XrcgSYAib37M8ouI2wKiaOJelbg7aiaiaWEmrSrbbTZXiaWU8szQyV8MTHeMtQqDQ/0', '15127045633', '9栋330', '1', '石家庄铁道大学', 'oIGUY0YjG1HsX2JC5l4GDR5Zype4');
INSERT INTO `users` VALUES ('100002', '20163620', '二哈的姑奶奶', 'https://wx.qlogo.cn/mmopen/vi_32/vvnlzsUfIB3UtibvghqIcPFnicRBCuaEbBZMnfFvan2zByKhqssVSklesXn9UiaDf7D2jMQEqq0HhnrUTCFnUudbw/132', null, null, '0', '石家庄铁道大学', 'oIGUY0SCNKe1nE4pnuUjBJ1gEw9Q');
INSERT INTO `users` VALUES ('100003', null, 'a朵％', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIF5gXA665dY6iaPC5iaU3frkfBrktOBE2BpZ2U4encBtxsE9n42xVMDdWhbSUjRsPicrpKR75120YCw/132', null, null, '0', '石家庄铁道大学', 'oIGUY0ZsUzUKDnXxF4ktp18LRMzs');
INSERT INTO `users` VALUES ('100004', '', '嘻', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJlJAgxLa4we4GBnadDSO5iaBk00avBiceDLGCZYmbMKQso83BWic0vKScGIkBfUGZNpvOG8G0fzQFWA/132', null, '石家庄', '0', '石家庄铁道大学', 'oIGUY0aSwO_k5zqjubn-QpfkZ8gs');
INSERT INTO `users` VALUES ('100005', '20163694', '娓娓动听', 'https://wx.qlogo.cn/mmopen/vi_32/dZj08ckjV0LwH84wIwwEgsicgL9mxmicfy99wE4CKqJ0iccrcX38dpZEh6sSle374zQIdf8GObLReoMWDMwl813oA/0', null, '石家庄', '0', '石家庄铁道大学', 'oIGUY0ce3y-3_7Bu7hbcQl4_pXcc');
INSERT INTO `users` VALUES ('100006', null, '哈', 'https://wx.qlogo.cn/mmopen/vi_32/54GloASLv1HsMicITGJZ4PtUVYBgyicRC1Gpicntkd9XibKBqLlUicttXkd7ThNBUXIxia4rPWBvtxR5tib9Dfasic2hYw/132', null, null, '1', '石家庄铁道大学', 'ozrQJ4x9UQbLFoJRvxOODz_SnJCs');

-- ----------------------------
-- View structure for first_message
-- ----------------------------
DROP VIEW IF EXISTS `first_message`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `first_message` AS select `m`.`id` AS `id`,`m`.`senduserid` AS `senduserid`,`m`.`reciveuserid` AS `reciveuserid`,`m`.`goodid` AS `goodid`,`m`.`status` AS `status`,`m`.`content` AS `content`,`m`.`time` AS `time`,`m`.`title` AS `title` from `message` `m` where (not(exists(select 1 from `message` where ((greatest(`message`.`senduserid`,`message`.`reciveuserid`) = greatest(`m`.`senduserid`,`m`.`reciveuserid`)) and (least(`message`.`senduserid`,`message`.`reciveuserid`) = least(`m`.`senduserid`,`m`.`reciveuserid`)) and (`message`.`time` > `m`.`time`))))) ;

-- ----------------------------
-- View structure for good
-- ----------------------------
DROP VIEW IF EXISTS `good`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `good` AS select `goods`.`id` AS `id`,`goods`.`goodname` AS `goodname`,`goods`.`goodownerid` AS `goodownerid`,`goods`.`price` AS `price`,`goods`.`description` AS `description`,`goods`.`image` AS `image`,`goods`.`num` AS `num`,`goods`.`catagory` AS `catagory`,`goods`.`time` AS `time`,`leave_msg`.`id` AS `lid`,`leave_msg`.`senduserid` AS `luserId`,`leave_msg`.`reciveuserid` AS `lreciveuserId`,`leave_msg`.`goodid` AS `lgoodid`,`leave_msg`.`content` AS `lcontent`,`leave_msg`.`time` AS `ltime`,`leave_msg`.`reply` AS `lreply`,`collect`.`userId` AS `userId`,`collect`.`goodId` AS `goodId` from ((`goods` left join `leave_msg` on((`goods`.`id` = `leave_msg`.`goodid`))) left join `collect` on((`goods`.`id` = `collect`.`goodId`))) ;
