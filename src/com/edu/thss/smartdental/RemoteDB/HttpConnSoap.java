package com.edu.thss.smartdental.RemoteDB;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpConnSoap {
	public ArrayList<String> GetWebService(String methodName, ArrayList<String> Parameters, ArrayList<String> ParValues) {
		ArrayList<String> Values = new ArrayList<String>();
		
		//ָ��URL��ַ��������ʹ�õ��ǳ�����  
        //�磺String ServerUrl = "http://10.0.2.2:11125/Service1.asmx";  
        String ServerUrl = "http://192.168.1.103/Service1.asmx";  
  
        //soapAction = �����ռ� + ������  
        String soapAction = "http://tempuri.org/" + methodName;  
  
        //ƴ��requestData  
        String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"  
                      + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"  
                      + "<soap:Body />";  
        String tps, vps, ts;  
        String mreakString = "";  
        mreakString = "<" + methodName + " xmlns=\"http://tempuri.org/\">";  
        for (int i = 0; i < Parameters.size(); i++)  
        {  
            tps = Parameters.get (i).toString();  
            //���ø÷����Ĳ���Ϊ.net webService�еĲ�������  
            vps = ParValues.get (i).toString();  
            ts = "<" + tps + ">" + vps + "</" + tps + ">";  
            mreakString = mreakString + ts;  
        }  
        mreakString = mreakString + "</" + methodName + ">";  
        String soap2 = "</soap:Envelope>";  
        String requestData = soap + mreakString + soap2;  
        //�������е����ݶ�����ƴ��requestData��������������͵�����  
  
        try  
        {  
            URL url = new URL (ServerUrl); //ָ����������ַ 
             
            //InputStream is = null;  
            HttpURLConnection con = (HttpURLConnection) url.openConnection();//������
            //con.connect();  
            //is = con.getInputStream();  
            
            byte[] bytes = requestData.getBytes ("utf-8"); //ָ�������ʽ�����Խ��������������  
            con.setDoInput (true); //ָ���������Ƿ��������  
            con.setDoOutput (true); //ָ���������Ƿ�������  
            con.setUseCaches (false); //ָ���������Ƿ�ֻ��caches  
            con.setConnectTimeout (6000); // ���ó�ʱʱ��  
            con.setRequestMethod ("GET"); //ָ�����ͷ�����������Post��Get��  
            con.setRequestProperty ("Content-Type", "text/xml;charset=utf-8"); //���ã����͵ģ���������  
            con.setRequestProperty ("SOAPAction", soapAction); //ָ��soapAction  
            con.setRequestProperty ("Content-Length", "" + bytes.length); //ָ�����ݳ��� 
            //con.connect();
            
            //��������  
            OutputStream outStream = con.getOutputStream();  
            outStream.write (bytes);  
            outStream.flush();  
            outStream.close();  
  
            //��ȡ����  
            InputStream inputStream = con.getInputStream();  
            Values = inputStreamtovaluelist(inputStream,methodName);
            return Values;  
  
            /** 
             * ���ൽ�˽����ˣ���ԭ����HttpConnSoap���̣���Ϊ����û�жԷ��ص�������������������ȫ����������inputStream�С� 
             * ��ԭ�������ǽ����ݽ�������ArrayList 
             * <String>��ʽ���ء���Ȼ�������޷����������������󣨷���ֵ�Ǹ������͵�List�� 
             */  
        }catch (Exception e)  
        {  
            e.printStackTrace();  
            return Values;  
        }  
	}

	public ArrayList<String> inputStreamtovaluelist(InputStream in, String MonthsName) throws IOException {
		StringBuffer out = new StringBuffer();
		String s1 = "";
		byte[] b = new byte[4096];
		ArrayList<String> Values = new ArrayList<String>();
		Values.clear();

		for (int n; (n = in.read(b)) != -1;) {
			s1 = new String(b, 0, n);
			out.append(s1);
		}

		System.out.println(out);
		String[] s13 = s1.split("><");
		//String[] s13 = out.toString().split("><");
		String ifString = MonthsName + "Result";
		String TS = "";
		String vs = "";

		Boolean getValueBoolean = false;
		for (int i = 0; i < s13.length; i++) {
			TS = s13[i];
			System.out.println(TS);
			int j, k, l;
			j = TS.indexOf(ifString);
			k = TS.lastIndexOf(ifString);

			if (j >= 0) {
				System.out.println(j);
				if (getValueBoolean == false) {
					getValueBoolean = true;
				} else {

				}

				if ((j >= 0) && (k > j)) {
					System.out.println("FFF" + TS.lastIndexOf("/" + ifString));
					//System.out.println(TS);
					l = ifString.length() + 1;
					vs = TS.substring(j + l, k - 2);
					//System.out.println("fff"+vs);
					Values.add(vs);
					System.out.println("�˳�" + vs);
					getValueBoolean = false;
					return Values;
				}

			}
			if (TS.lastIndexOf("/" + ifString) >= 0) {
				getValueBoolean = false;
				return Values;
			}
			if ((getValueBoolean) && (TS.lastIndexOf("/" + ifString) < 0) && (j < 0)) {
				k = TS.length();
				//System.out.println(TS);
				vs = TS.substring(7, k - 8);
				//System.out.println("f"+vs);
				Values.add(vs);
			}

		}

		return Values;
	}

}
