package com.brainnet.smartremote.device.protocol;

public class DeviceEnumProtocol extends ProtocolBase {

	public static final int VALUE_DATA = 1; // 设备名称：20 字节存储，可用UTF-8 或其他方式编码；
	public static final int VALUE_STATUS = 2; // 状态代码：00/FF数据无效；01 设备正常；02
												// 设备操作代码错误；
	public static final int VALUE_ID = 3; // 设备ID：002.018.000.001
	public static final int VALUE_PWD = 4; // 设备操作密码：1234；
	public static final int VALUE_TYPE = 5; //
	public static final int VALUE_EXT = 6; //

	public static final int VALUE_DATA_NUM = 0;
	public static final int VALUE_DATA_VALUE = 1; // 所读取条目：00 or 01，第0 或1
													// 条；（00~31）

	public static final String VALUE_TYPE_SWITCH = "70"; //
	// 70 表示开关状态，内容如下：
	// 80 转成二进制1000 0000 表示8 路开关输出状态；
	// 81 表示上次操作为按键高电平触发；
	// 01 表示发送包号，自动递增；Protocol
	// 00 暂时未用；
	public static final String VALUE_TYPE_C = "71"; //
	// 71 表示温湿度数据，内容如下：（DHT11 为例）
	// 19 00 表示温度整数25，温度小数0；
	// 21 00 表示湿度整数33，湿度小数0；
	public static final String VALUE_TYPE_IRDA = "72"; //
	// 72 表示红外遥控数据：
	// 80 表示正常发出，00 表示记录为空，01 表示进入学习，02
	// 表示退出学习；
	// 81 表示发送的红外记录数；（00~FF）
	// 01 表示发送包号，自动递增；
	// 00 暂时未用；
	public static final String VALUE_TYPE_IC = "73"; //
	// 设备状态类别及状态值：
	// 73 表示读取IC 卡数据；
	// 80 表示读取字节数；
	// 00 暂时未用；
	// 01 表示发送包号，自动递增；
	// 00 暂时未用；
	// 最后分隔符“-”后为IC 卡数据；
	// 10 表示遥控按键；
	// 01 表示按键代码（0~255）；
	// 后6 位字符无效；

	// 【红外转发操作代码】
	// 10xx：发送第xx 条记录；
	// 11xx：第xx 条记录开始学习：
	// 12xx：第xx 条记录退出学习；
	// 13xx：查询外设状态；

	// RDAT-00&0123456789ABCDEF0123456789ABCDEF12345678-01-002.003.003.053-1234-70-00800500

	EnumData mEnumData;
	@Override
	public void setText(String text) {
		super.setText(text);
		mEnumData = new EnumData(getValue(VALUE_DATA));
	}
	
	
	public EnumData getData() {
		return mEnumData;
	}

	public class EnumData {

		public EnumData(String data) {
			setData(data);
		}

		public boolean setData(String data) {

			name = "";
			index = -1;
			ext = "";

			if (data == null) {
				return false;
			}
			String[] array = data.split("&");

			if (array == null) {
				return false;
			}

			if (array.length != 2) {
				return false;
			}
			
			try {
				index = Integer.valueOf(array[0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			name = array[0];
			
			ext = array[1];
			return true;
		}

		public String name = "";
		public int index = -1;
		public String ext = "";
	}

	public boolean isValid() {

		return mEnumData.index != -1;
	}

}
