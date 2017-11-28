package kr.ac.uos.jms;

import static junit.framework.Assert.assertEquals;
import kr.ac.uos.software_project.aeat.JMS_Sender;
import kr.ac.uos.software_project.aeat.Main;
import org.junit.Test;


public class JMS_Test {
	
	@Test
	public void testPublisher() {
		JMS_Sender jmsPublisher = new JMS_Sender();
	
		String aeatXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
				"<alert xmlns=\"urn:oasis:names:tc:emergency:cap:1.2\">\r\n" + 
				"  <identifier>국민안전처1508190001</identifier>\r\n" + 
				"  <sender>civilalerter@korea.kr</sender>\r\n" + 
				"  <sent>2015-08-19T14:00:00+09:00</sent>\r\n" + 
				"  <status>Actual</status>\r\n" + 
				"  <msgType>Alert</msgType>\r\n" + 
				"  <scope>Public</scope>\r\n" + 
				"  <code>대한민국정부1.0</code>\r\n" + 
				"  <info>\r\n" + 
				"    <language>ko-KR</language>\r\n" + 
				"    <category>Security</category>\r\n" + 
				"    <event>민방공 공습경보</event>\r\n" + 
				"	<responseType>Shelter</responseType>\r\n" + 
				"    <urgency>Immediate</urgency>\r\n" + 
				"    <severity>Severe</severity>\r\n" + 
				"    <certainty>Observed</certainty>\r\n" + 
				"    <eventCode>\r\n" + 
				"      <valueName>TTAS.KO-07.0046/R5 재난 종류 코드</valueName>\r\n" + 
				"      <value>AL1</value>\r\n" + 
				"    </eventCode>\r\n" + 
				"    <senderName>충청북도 도지사</senderName>\r\n" + 
				"    <headline>민방공 공습경보 발표</headline>\r\n" + 
				"    <description>\r\n" + 
				"2015년 8월 19일 오후 14시 현재, 청주시 상공에 적기 출현, 다량의 폭탄을 도심지 서원구 일대에 투하.\r\n" + 
				"동시에 화학탄두가 장착된 미사일 공습으로 청원구 공업단지 내 화생방 오염상황 발생.\r\n" + 
				"공업단지 내 XX화학 중앙공장동 화학탄두 탑재 미사일 피폭으로 부상자 다수 및 오염상황 발생\r\n" + 
				"화학작용제가 남서풍을 타고 확산 중\r\n" + 
				"    </description>\r\n" + 
				"	<instruction>\r\n" + 
				"인근 대피소로 신속하고 안전하게 대피, 방독면 착용. 부상자 인근 유관기관에 신고 및 응급조치\r\n" + 
				"	</instruction>\r\n" + 
				"    <contact>청원구청 총무과, 043-201-8062</contact>\r\n" + 
				"	<parameter>\r\n" + 
				"		<valueName>CBSText</valueName>\r\n" + 
				"		<value>\r\n" + 
				"2015년 8월 19일 오후 14시 현재, 청주시 상공에 적기 출현, 다량의 폭탄을 도심지 서원구 일대에 투하. 화학작용제가 남서풍을 타고 확산 중\r\n" + 
				"		</value>\r\n" + 
				"	</parameter>\r\n" + 
				"	<parameter>\r\n" + 
				"		<valueName>DMBPriority</valueName>\r\n" + 
				"		<value>3</value>\r\n" + 
				"	</parameter>\r\n" + 
				"	<parameter>\r\n" + 
				"		<valueName>CBSPriority</valueName>\r\n" + 
				"		<value>0</value>\r\n" + 
				"	</parameter>\r\n" + 
				"    <area>\r\n" + 
				"      <areaDesc>충청북도 청주시</areaDesc>\r\n" + 
				"      <geocode>\r\n" + 
				"        <valueName>행정구역코드</valueName>\r\n" + 
				"        <value>4311000000</value>\r\n" + 
				"      </geocode>\r\n" + 
				"    </area>\r\n" + 
				"  </info>\r\n" + 
				"</alert>";
		
		assertEquals(aeatXml, jmsPublisher.readCap(Main.XML_PATH));
	}
}
