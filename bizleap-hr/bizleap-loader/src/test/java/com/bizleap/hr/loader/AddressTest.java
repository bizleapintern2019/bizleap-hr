package com.bizleap.hr.loader;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.junit.Assert;
import com.bizleap.commons.domain.entity.Address;
import com.bizleap.hr.loader.impl.test.ServiceTest;

public class AddressTest extends ServiceTest {
	@Autowired
	private DataLoader dataLoader;
	private List<Address> addressList;

	@Test
	public void parseAddressTest() throws Exception {
		addressList = dataLoader.loadAddress();
		Assert.assertTrue(addressList != null && addressList.size() == 24);
		for (Address address : addressList) {
			switch (address.getBoId()) {
			case "ADR001":
				Assert.assertEquals(address.getPermanentAddress(), "No.11,zinziyar Str");
				Assert.assertEquals(address.getContactAddress(), "No.79,zinziyar Str");
				Assert.assertEquals(address.getCity(), "Yangon");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;
			case "ADR002":
				Assert.assertEquals(address.getPermanentAddress(), "No.(58),Moe Ma Kha Street");
				Assert.assertEquals(address.getContactAddress(), "No.(58),Moe Ma Kha Street");
				Assert.assertEquals(address.getCity(), "Mingaladone Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;
			case "ADR003":
				Assert.assertEquals(address.getPermanentAddress(), "No.(59),Wizarya Street");
				Assert.assertEquals(address.getContactAddress(), "No.(59),Wizarya Street");
				Assert.assertEquals(address.getCity(), "Yangon");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;
			case "ADR004":
				Assert.assertEquals(address.getPermanentAddress(), "No.(80),Bosan Street");
				Assert.assertEquals(address.getContactAddress(), "No.(80),Bosan Street");
				Assert.assertEquals(address.getCity(), "Yangon");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;
			case "ADR005":
				Assert.assertEquals(address.getPermanentAddress(), "No.(76),Thanlan Street");
				Assert.assertEquals(address.getContactAddress(), "No.(76),www.email.com");
				Assert.assertEquals(address.getCity(), "Bago");
				Assert.assertEquals(address.getState(), "Bago");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;
			case "ADR006":
				Assert.assertEquals(address.getPermanentAddress(), "Building(11),Room(4),No.(1),industrial Road");
				Assert.assertEquals(address.getContactAddress(), "Building(11),Room(4),No.(1),industrial Road");
				Assert.assertEquals(address.getCity(), "Bahan Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR007":
				Assert.assertEquals(address.getPermanentAddress(), "No.(56),Oak Lan Street");
				Assert.assertEquals(address.getContactAddress(), "No.(56),Oak Lan Street");
				Assert.assertEquals(address.getCity(), "San Chaung Townshiop");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR008":
				Assert.assertEquals(address.getPermanentAddress(), "Aung Chan Than Street,North of Thandanpin");
				Assert.assertEquals(address.getContactAddress(), "Aung Chan Than Street,North of Thandanpin");
				Assert.assertEquals(address.getCity(), "Hmawbi Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR009":
				Assert.assertEquals(address.getPermanentAddress(), "No.(54),Oo ohn Shwe Street,SatKaLay Yard");
				Assert.assertEquals(address.getContactAddress(), "No.(54),Oo ohn Shwe Street,SatKaLay Yard");
				Assert.assertEquals(address.getCity(), "Insein Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR010":
				Assert.assertEquals(address.getPermanentAddress(), "No.(1182),Rose Street,Kamakasit Yard");
				Assert.assertEquals(address.getContactAddress(), "No.(1182),Rose Street,Kamakasit Yard");
				Assert.assertEquals(address.getCity(), "Tharkayta Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR011":
				Assert.assertEquals(address.getPermanentAddress(), "41/354,Pone Myat Street");
				Assert.assertEquals(address.getContactAddress(), "41/354,Pone Myat Street");
				Assert.assertEquals(address.getCity(), "North Dagon Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR012":
				Assert.assertEquals(address.getPermanentAddress(), "No.229,4th Street,1/Ka Ward");
				Assert.assertEquals(address.getContactAddress(), "No.229,4th Street,1/Ka Ward");
				Assert.assertEquals(address.getCity(), "Mingaladon Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR013":
				Assert.assertEquals(address.getPermanentAddress(), "(A)224/4th floor,Anomar(18) Street");
				Assert.assertEquals(address.getContactAddress(), "(A)224/4th floor,Anomar(18) Street");
				Assert.assertEquals(address.getCity(), "Tharkayta Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR014":
				Assert.assertEquals(address.getPermanentAddress(), "No.(61),BoBaHtoo(11) Street");
				Assert.assertEquals(address.getContactAddress(), "No.(61),BoBaHtoo(11) Street");
				Assert.assertEquals(address.getCity(), "Hlaing Thar Yar Townshiop");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR015":
				Assert.assertEquals(address.getPermanentAddress(), "No.(120)/Room(19),Yangon-Insein Road,9 Quarter");
				Assert.assertEquals(address.getContactAddress(), "No.(120)/Room(19),Yangon-Insein Road,9 Quarter");
				Assert.assertEquals(address.getCity(), "Hlaing Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR016":
				Assert.assertEquals(address.getPermanentAddress(), "No.(109),1st floor,D block ");
				Assert.assertEquals(address.getContactAddress(), "No.(109),1st floor,D block ");
				Assert.assertEquals(address.getCity(), "North Okalapa Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR017":
				Assert.assertEquals(address.getPermanentAddress(), "No.(74),zinziyar Street");
				Assert.assertEquals(address.getContactAddress(), "No.(74),zinziyar Street");
				Assert.assertEquals(address.getCity(), "Insein Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR018":
				Assert.assertEquals(address.getPermanentAddress(), "No.(1658)/1(Anawmar)");
				Assert.assertEquals(address.getContactAddress(), "No.(1658)/1(Anawmar)");
				Assert.assertEquals(address.getCity(), "Tharkayta Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR019":
				Assert.assertEquals(address.getPermanentAddress(), "No.(106),101 Street");
				Assert.assertEquals(address.getContactAddress(), "No.(106),101 Street");
				Assert.assertEquals(address.getCity(), "Mingalartownnyunt Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR020":
				Assert.assertEquals(address.getPermanentAddress(), "No.(390),sakawut(1) Street");
				Assert.assertEquals(address.getContactAddress(), "No.(390),sakawut(1) Street");
				Assert.assertEquals(address.getCity(), "North Okalapa Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR021":
				Assert.assertEquals(address.getPermanentAddress(), "No.(333),Ingyin Myaing Street,105 Yard");
				Assert.assertEquals(address.getContactAddress(), "No.(333),Ingyin Myaing Street,105 Yard");
				Assert.assertEquals(address.getCity(), "South Dagon Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR022":
				Assert.assertEquals(address.getPermanentAddress(), "No.39,1st floor,87 Street");
				Assert.assertEquals(address.getContactAddress(), "No.39,1st floor,87 Street");
				Assert.assertEquals(address.getCity(), "Mingalartownnyunt Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR023":
				Assert.assertEquals(address.getPermanentAddress(), "No.103(C),Maharbawga Street");
				Assert.assertEquals(address.getContactAddress(), "No.103(C),Maharbawga Street");
				Assert.assertEquals(address.getCity(), "Mayangone Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			case "ADR024":
				Assert.assertEquals(address.getPermanentAddress(), "No.(34),A Ingyin Myaing,5th Street");
				Assert.assertEquals(address.getContactAddress(), "No.(34),A Ingyin Myaing,5th Street");
				Assert.assertEquals(address.getCity(), "Thingangyun Township");
				Assert.assertEquals(address.getState(), "Yangon");
				Assert.assertEquals(address.getCountry(), "Myanmar");
				break;

			default:
				Assert.assertTrue(false);
			}
		}
	}
}
