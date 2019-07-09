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

	@Test
	public void testParseEmployee() throws Exception{
		 testAddressList(dataLoader.loadAddress());
	}
	
	public int assertAddress(Address address,String boId,String permanentAddress,String contactAddress,String city,String state,String country) {
		if(address.getBoId().equals(boId)) {
			Assert.assertEquals(address.getPermanentAddress(), permanentAddress);
			Assert.assertEquals(address.getContactAddress(), contactAddress);
			Assert.assertEquals(address.getCity(), city);
			Assert.assertEquals(address.getState(), state);
			Assert.assertEquals(address.getCountry(), country);
			return 1;
		}
		return 0;
	}
	
	public void testAddressList(List<Address> addressList) throws Exception {
		addressList = dataLoader.loadAddress();
		Assert.assertTrue(addressList != null && addressList.size() == 24);
		int successCount=0;
		for(Address address : addressList) {
			successCount += assertAddress(address,"ADR001","No.11,zinziyar Str","No.79,zinziyar Str","Yangon","Yangon","Myanmar");
			successCount += assertAddress(address,"ADR002","No.(58),Moe Ma Kha Street","No.(58),Moe Ma Kha Street","Mingaladone Township","Yangon","Myanmar");
			successCount += assertAddress(address,"ADR003","No.(59),Wizarya Street","No.(59),Wizarya Street","Yangon","Yangon","Myanmar");
			successCount += assertAddress(address,"ADR004","No.(80),Bosan Street","No.(80),Bosan Street","Yangon","Yangon","Myanmar");
			successCount += assertAddress(address,"ADR005","No.(76),Thanlan Street","No.(76),www.email.com","Bago","Bago","Myanmar");
			successCount += assertAddress(address,"ADR006","Building(11),Room(4),No.(1),industrial Road","Building(11),Room(4),No.(1),industrial Road","Bahan Township","Yangon","Myanmar");
			successCount += assertAddress(address,"ADR007","No.(56),Oak Lan Street","No.(56),Oak Lan Street","San Chaung Townshiop","Yangon","Myanmar");
			successCount += assertAddress(address,"ADR008","Aung Chan Than Street,North of Thandanpin","Aung Chan Than Street,North of Thandanpin","Hmawbi Township","Yangon","Myanmar");
			successCount += assertAddress(address,"ADR009","No.(54),Oo ohn Shwe Street,SatKaLay Yard","No.(54),Oo ohn Shwe Street,SatKaLay Yard","Insein Township","Yangon","Myanmar");
			successCount += assertAddress(address,"ADR010","No.(1182),Rose Street,Kamakasit Yard","No.(1182),Rose Street,Kamakasit Yard","Tharkayta Township","Yangon","Myanmar");
			successCount += assertAddress(address,"ADR011","41/354,Pone Myat Street","41/354,Pone Myat Street","North Dagon Township","Yangon","Myanmar");
			successCount += assertAddress(address,"ADR012","No.229,4th Street,1/Ka Ward","No.229,4th Street,1/Ka Ward","Mingaladon Township","Yangon","Myanmar");
			successCount += assertAddress(address,"ADR013","(A)224/4th floor,Anomar(18) Street","(A)224/4th floor,Anomar(18) Street","Tharkayta Township","Yangon","Myanmar");
			successCount += assertAddress(address,"ADR014","No.(61),BoBaHtoo(11) Street","No.(61),BoBaHtoo(11) Street","Hlaing Thar Yar Townshiop","Yangon","Myanmar"); 
			successCount += assertAddress(address,"ADR015","No.(120)/Room(19),Yangon-Insein Road,9 Quarter","No.(120)/Room(19),Yangon-Insein Road,9 Quarter","Hlaing Township","Yangon","Myanmar"); 
			successCount += assertAddress(address,"ADR016","No.(109),1st floor,D block","No.(109),1st floor,D block","North Okalapa Township","Yangon","Myanmar"); 
			successCount += assertAddress(address,"ADR017","No.(74),zinziyar Street","No.(74),zinziyar Street","Insein Township","Yangon","Myanmar"); 
			successCount += assertAddress(address,"ADR018","No.(1658)/1(Anawmar)","No.(1658)/1(Anawmar)","Tharkayta Township","Yangon","Myanmar"); 
			successCount += assertAddress(address,"ADR019","No.(106),101 Street","No.(106),101 Street","Mingalartownnyunt Township","Yangon","Myanmar"); 
			successCount += assertAddress(address,"ADR020","No.(390),sakawut(1) Street","No.(390),sakawut(1) Street","North Okalapa Township","Yangon","Myanmar"); 
			successCount += assertAddress(address,"ADR021","No.(333),Ingyin Myaing Street,105 Yard","No.(333),Ingyin Myaing Street,105 Yard","South Dagon Township","Yangon","Myanmar"); 
			successCount += assertAddress(address,"ADR022","No.39,1st floor,87 Street","No.39,1st floor,87 Street","Mingalartownnyunt Township","Yangon","Myanmar"); 
			successCount += assertAddress(address,"ADR023","No.103(C),Maharbawga Street","No.103(C),Maharbawga Street","Mayangone Township","Yangon","Myanmar"); 
			successCount += assertAddress(address,"ADR024","No.(34),A Ingyin Myaing,5th Street","No.(34),A Ingyin Myaing,5th Street","Thingangyun Township","Yangon","Myanmar");
			Assert.assertTrue(successCount==1);
			successCount=0;
		}
	}
}
