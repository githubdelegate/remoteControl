package com.brainnet.smartremote.device.protocol;

public class DeviceEnumProtocol extends ProtocolBase {

	public static final int VALUE_DATA = 1; // �豸���ƣ�20 �ֽڴ洢������UTF-8 ��������ʽ���룻
	public static final int VALUE_STATUS = 2; // ״̬���룺00/FF������Ч��01 �豸������02
												// �豸�����������
	public static final int VALUE_ID = 3; // �豸ID��002.018.000.001
	public static final int VALUE_PWD = 4; // �豸�������룺1234��
	public static final int VALUE_TYPE = 5; //
	public static final int VALUE_EXT = 6; //

	public static final int VALUE_DATA_NUM = 0;
	public static final int VALUE_DATA_VALUE = 1; // ����ȡ��Ŀ��00 or 01����0 ��1
													// ������00~31��

	public static final String VALUE_TYPE_SWITCH = "70"; //
	// 70 ��ʾ����״̬���������£�
	// 80 ת�ɶ�����1000 0000 ��ʾ8 ·�������״̬��
	// 81 ��ʾ�ϴβ���Ϊ�����ߵ�ƽ������
	// 01 ��ʾ���Ͱ��ţ��Զ�������Protocol
	// 00 ��ʱδ�ã�
	public static final String VALUE_TYPE_C = "71"; //
	// 71 ��ʾ��ʪ�����ݣ��������£���DHT11 Ϊ����
	// 19 00 ��ʾ�¶�����25���¶�С��0��
	// 21 00 ��ʾʪ������33��ʪ��С��0��
	public static final String VALUE_TYPE_IRDA = "72"; //
	// 72 ��ʾ����ң�����ݣ�
	// 80 ��ʾ����������00 ��ʾ��¼Ϊ�գ�01 ��ʾ����ѧϰ��02
	// ��ʾ�˳�ѧϰ��
	// 81 ��ʾ���͵ĺ����¼������00~FF��
	// 01 ��ʾ���Ͱ��ţ��Զ�������
	// 00 ��ʱδ�ã�
	public static final String VALUE_TYPE_IC = "73"; //
	// �豸״̬���״ֵ̬��
	// 73 ��ʾ��ȡIC �����ݣ�
	// 80 ��ʾ��ȡ�ֽ�����
	// 00 ��ʱδ�ã�
	// 01 ��ʾ���Ͱ��ţ��Զ�������
	// 00 ��ʱδ�ã�
	// ���ָ�����-����ΪIC �����ݣ�
	// 10 ��ʾң�ذ�����
	// 01 ��ʾ�������루0~255����
	// ��6 λ�ַ���Ч��

	// ������ת���������롿
	// 10xx�����͵�xx ����¼��
	// 11xx����xx ����¼��ʼѧϰ��
	// 12xx����xx ����¼�˳�ѧϰ��
	// 13xx����ѯ����״̬��

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
