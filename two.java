import java.util.*;
/**
 * 
 * @author Amir abbasi
 * project 2
 *
 */
public class two {
	public static void main(String[] args) {
		///////Tarif Moteghyer ha/////////////
		Scanner input=new Scanner(System.in);
		String voroodi=input.nextLine();
		int i,j;
		String [] dastoorat=voroodi.split("_");//araye ea as dastoorat 
		String answers[]=new String [dastoorat.length];//arayeye zakhire javabhaye datoorat
		/////////////////
		
		
		///////////////////////////////////////////////////////////////ghesmate mohasebat//////////////////////////////////////////////////////////////////////
		//har yek az deraye haye dastoorat[] ke shamel dastoor va parameterha ast be FunctionReader() ferestade mishavad va javab dar answer[] gozashte mishavad
		for(i=0;i<dastoorat.length;i++) 
			answers[i]=FunctionReader(dastoorat[i]);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		///////////namayesh javab ha//////
		for(i=0;i<answers.length;i++)
			System.out.println(answers[i]);
		//////////////////////////////////
	}
	
	/*
	 * FunctionReade():vazife tashkhis voroodi ha va analyz dastooarat ra bar ohde darad.asool koli an bar asas method tabe bazgashti ast.yani ebteda check mikonad dar "dastoor" aya "(" peyda mishavad ya kheiyr.agar peyda shavad yani voroodi shamel dastoori
	 * ast ke bayad analyz shavad va mohasebat anjam shavad va javab bargardande shavad.va agar shamel "(" nabashad be soorat kham ast va yek adad ast;ke pas dar in soorat haman adad be onvan javab bargardande mishavad.
	 */
	public static String FunctionReader(String dastoor) {
		String function="",number1,number2;//number1:parametre aval,number2:paramtere dovom
		int i,count=0;//count:shamarande tedade ( baraye tashkise parametr ha
		boolean cama=false;//flagi ast baraye tashkis inke aya be parameter badi reside im ya kheyr;
		
		if(dastoor.indexOf("(")==-1)//check kardane noe parameterha(adad boodan ya dastoor boodan)
			return dastoor;
		else
		{	
			number1="";
			number2="";
			count=0;
			for(i=0;i<dastoor.length()-1;i++) {
				if(dastoor.charAt(i)=='(' && count!=0) //shorooe dastoorate fareei(paramter khodash dastoor ast)
					count++;
				if(dastoor.charAt(i)=='(' && count==0) {//shorooe dastoore asli
					count++;
					if(function=="subNumber") //estesna baraye subNumber;chon se voroodi migirad
						break;
					continue;
				}
				if(dastoor.charAt(i)==')' && count!=1)
					count--;
				if(dastoor.charAt(i)==',' && count==1) {//payane khandane parametre aval
					cama=true;
					continue;}
				if(count==0)//yani pointer ghable parantez ast va dastoor khande mishavad
					function+=dastoor.charAt(i);
				if(count!=0 && cama==false)//pointer rooye paramtere aval ast
					number1+=dastoor.charAt(i);
				if(cama==true)//pointer rooye parametre dovom ast
					number2+=dastoor.charAt(i);
					
			}
			
			number1=FunctionReader(number1);//check kardane paramter
			number2=FunctionReader(number2);//check kadane paramter

			switch(function) {
			case "add":
				return add(number1,number2);// jam
			case "sub":
				return sub(number1,number2);//tafrigh
			case "pow":
				return power(number1,number2);//tavan
			case "fact":
				return factorial(number1);//factorial
			case "nextPerm":
				return nextPerm(number1);
			case "mod"://baghimande
				return String.valueOf(Long.parseLong(number1)%Long.parseLong(number2));
			case "rep"://tekrar adad aval be tedade adade dovom
				return rep(number1,number2);
			case "isPalindromes":
				return String.valueOf(isPalindromes(number1,number2));
			case "sumOfDigits":
				return sumOfDigits(number1);
			case "rotate"://ghalbe adad
				return rotate(number1,number2);
			case "sort"://moratab sazi
				return Sort(number1);
			case "subNumber":
				return subNumber(dastoor);
			case "split":
				return split(number1,number2);
			case "indexOf":
				return indexOf(number1,number2);
			}
			return "0";	
		}
	}
	/**
	 * adad be andaze tedad tekrar mishavd
	 * @param number1 adad
	 * @param number2 tedade tekrar
	 * @return String
	 */
	public static String rep(String number1,String number2) {
		int i;
		String answer="";
		for(i=1;i<=Integer.parseInt(number2);i++)
			answer+=number1;
		return answer;
	}
	/**
	 * @param number adad
	 * @param tavan tavan
	 * @return String
	 */
public static String power(String number,String tavan) {
	int power,i;
	String answer="1";
	power=Integer.parseInt(tavan);
	for(i=0;i<power;i++)
		answer=zarb(answer,number);
	
		return answer;
	
}
/**
 * 
 * @param number adad
 * @return String
 */
public static String factorial(String number) {
	int i;
	String answer="1";
	for(i=1;i<=Integer.parseInt(number);i++)
		answer=zarb(String.valueOf(answer),String.valueOf(i));
	return String.valueOf(answer);
}
/**
 * be adad yek vahed yek vahed ezafe mikonad ta vaghti ka be adadi ba arqam adade avalie tabdil shavad
 * @param number
 * @return String
 */
public static String  nextPerm(String number) {
			int count,j;
	String found=number,tmp;
	boolean flag=true;//flage peyda shodan adad
	while(flag==true) {
		if(found.length()!=number.length())//vojood nadarad
			return "-1";
		count=0;
		found=add(found,"1");//yek vahed be adad ezafe mishavad
		tmp=found;//analiz rooye tmp anjam mishavad ta "found" kharab nashavad
		for(j=0;j<number.length();j++) {//tak take arqame adad dar "tmp" gashte mishavad
			if(tmp.indexOf(number.charAt(j))!=-1) {
				count++;
				/////dar edame raqame peyda shode az "tmp" hazf mishavad
				if(tmp.indexOf(number.charAt(j))==tmp.length()-1)
					tmp=tmp.substring(0,tmp.length()-1);
				else
				tmp=tmp.substring(0,tmp.indexOf(number.charAt(j)))+tmp.substring(tmp.indexOf(number.charAt(j))+1,tmp.length());
				////
		}}
		if(count==number.length()) //adad peyda shode
			flag=false;
	}
	return found;
}
/**
 * 
 * @param parametre aval
 * @param parametre dovom
 * @return boolean
 */
public static boolean isPalindromes(String number1,String number2) {
	int i,size;
	if(number1.length()!=number2.length())
		return false;
	else
	{size=number1.length();
		for(i=0;i<size;i++) {
			if(number1.charAt(i)!=number2.charAt(size-1-i))
			{
				return false;
			}
		}
	return true;}
}
/**
 * jame arqam adad ra midahad
 * @param number
 * @return String
 */
public static String sumOfDigits(String number) {
	int sum=0,i;
	for(i=0;i<number.length();i++)
		sum+=Integer.parseInt(String.valueOf(number.charAt(i)));
	return String.valueOf(sum);
}
/**
 * 
 * @param adad
 * @param parametre shift
 * @return String
 */
public static String rotate(String number1,String number2) {
	String answer[]=new String [number1.length()];//javab be soorate araye
	String tmp[]=new String [number1.length()];//copy az adade avalie
	String finalAnswer="";//javab be soorate String baraye return
	int i,j;
	long tmpShift=Math.abs(Long.parseLong(number2))%number1.length();//tedade shift
	int shift=(int)tmpShift;
	for(i=0;i<number1.length();i++) {
		tmp[i]=String.valueOf(number1.charAt(i));
		answer[i]=tmp[i];
	}
		if(number2.charAt(0)=='-') {//shift be chap
	for(i=0;i<number1.length();i++) {
		answer[i]=tmp[(i+shift)%number1.length()];
}}
		else
{//shift be rast
			for(i=0;i<number1.length();i++) {
	answer[i]=tmp[(i+number1.length()-shift)%number1.length()];}
			}
for(i=0;i<answer.length;i++)//finalAnswer sakhte mishavad
	finalAnswer+=answer[i];
		
return finalAnswer;
}
/**
 * az tariqe "bubble sort" adad ra sort mikonad
 * @param dastoor
 * @return String
 */
public static String Sort(String number) {
String tmpAns[]=new String [number.length()];
String tmp,answer="";//answer:javab be soorate String
int i,j;
for(i=0;i<tmpAns.length;i++) 
	tmpAns[i]=String.valueOf(number.charAt(i));
for(i=0;i<tmpAns.length;i++) {
	for(j=0;j<tmpAns.length-1;j++) {
		if(Integer.parseInt(tmpAns[j])>Integer.parseInt(tmpAns[j+1])) {
			tmp=tmpAns[j];
			tmpAns[j]=tmpAns[j+1];
			tmpAns[j+1]=tmp;
		}
	}
}
for(i=0;i<tmpAns.length;i++) {//answer sakhte mishavad
	if(!tmpAns[i].equals("0"))//sefr az aval hazf mishavad
	answer+=tmpAns[i];}
return answer;

}
/**
 * SubString mikonad
 * @param dastoor
 * @return String
 */
public static String subNumber(String dastoor) {
	String tmp=dastoor.substring(10,dastoor.length()-1);
	String tmpInputs[]=tmp.split(",");
	String str=tmpInputs[0];
	int begin=Integer.parseInt(tmpInputs[1]),end=Integer.parseInt(tmpInputs[2])-1,i;
	String answer="";
	for(i=begin;i<=end;i++)
		answer+=str.charAt(i);
	return answer;
}
/**
 * split mikonad(bejaye parameter _ migozarad)
 * @param adad
 * @param parametre split
 * @return String
 */
public static String split(String number1,String number2) {
String finalAnswer="";
int i;
while(number1.indexOf(number2)!=-1) {
	for(i=0;i<number1.indexOf(number2);i++)
	{
		finalAnswer+=String.valueOf(number1.charAt(i));
		
	}finalAnswer+="_";
	number1=number1.substring(number1.indexOf(number2)+number2.length());
}
finalAnswer+=number1;
return finalAnswer;
}
/**
 * jam
 * @param adade aval
 * @param adade dovom
 * @return String
 */
public static String add(String a,String b) {
	int i,tmp;//tmp:baqimande jam
	String answer="",sign="";//sign:alamte javab
	if(a.charAt(0)=='-' && b.charAt(0)!='-')//add(-a,b)=sub(b,a)
		return sub(b,a.substring(1,a.length()));
	if(b.charAt(0)=='-' && a.charAt(0)!='-')//add(a,-b)=sub(a,b)
		return sub(a,b.substring(1,b.length()));
	if(a.charAt(0)=='-' && b.charAt(0)=='-')
	{
		sign="-";
		a=a.substring(1,a.length());//manfi az ebteda hazf mishavad
		b=b.substring(1,b.length());//manfi az ebteda hazf mishavad
	}
	///////toole do adad yeksan mishavad
	if(a.length()<b.length())
	{
		while(a.length()!=b.length())
			a="0"+a;
	}
	if(b.length()<a.length())
	{
		while(a.length()!=b.length())
			b="0"+b;
	}
	////////
	tmp=0;
	for(i=a.length()-1;i>=0;i--) {//algoritme tafriq

		answer=String.valueOf(((int)a.charAt(i)+(int)b.charAt(i)-96+tmp)%10)+answer;
		tmp=((int)a.charAt(i)+(int)b.charAt(i)-96+tmp)/10;
	}
	if(tmp!=0) {
		answer=String.valueOf(tmp)+answer;
	}
	return sign+answer;
}
/**
 * 
 * @param a
 * @param b
 * @return
 */
public static String sub(String a,String b) {
	int i,tmp;
	String answer="",max="",min="",sign="";//sign:alamate javab;max:adade bozorgtar az nazare bozorgi;min:adade koochektar az nazare bozorgi;hamvare "min" az "max" kam mishavad
	if(a.charAt(0)!='-' && b.charAt(0)=='-')//sub(a,-b)=add(a,b)
		return add(a,b.substring(1,b.length()));
	if(a.charAt(0)=='-' && b.charAt(0)!='-')//sub(-a,b)=add(-a,-b)
		return add(a,"-"+b);
	if(a.charAt(0)=='-'&& b.charAt(0)=='-') {//sub(-a,-b)=sub(b,a);pas a,b bayad mosbat sepas swap shavand
		String temp;
		temp=a.substring(1,a.length());
		a=b.substring(1,b.length());
		b=temp;
	}
	////////toole do adad yeksan mishavad
	if(a.length()<b.length())
	{
		while(a.length()!=b.length())
			a="0"+a;
		max=b;
		min=a;
		sign="-";
	}
	else if(b.length()<a.length())
	{
		while(a.length()!=b.length())
			b="0"+b;
		max=a;
		min=b;
		sign="";
	}
	//////////
	else {
		for(i=0;i<a.length();i++) {
			if((int)a.charAt(i)>(int)b.charAt(i)) {
				max=a;
				min=b;
				sign="";
				break;
			}
			if((int)b.charAt(i)>(int)a.charAt(i)) {
				max=b;
				min=a;
				sign="-";
				break;
			}
		}
		if(i==a.length())
			return "0";
	}
	tmp=0;
	for(i=max.length()-1;i>=0;i--) {
		
		if((int)max.charAt(i)-(int)min.charAt(i)+tmp<0){
			answer=String.valueOf(((int)max.charAt(i)-(int)min.charAt(i)+tmp+10))+answer;
			tmp=-1;
			
		}
		else {
			answer=String.valueOf(((int)max.charAt(i)-(int)min.charAt(i)+tmp))+answer;
			tmp=0;}
	}
	i=0;
	//////////sefr ha az ebteda hazf mishavand
		while(answer.charAt(i)=='0' && answer.length()!=1) {
			i++;
		}
		answer=answer.substring(i,answer.length());
	////////
	return sign+answer;
}
/**
 * zarb do adad ra piade sazi mikonad
 * @param a adade aval
 * @param b adade dovom
 * @return hasel be soorate String
 */
public static String zarb(String a,String b) {
	
	int i,j,sign=0;//sign:shomarande tedade -
	
	/////////shomareshe tedade manfiha//////
	if(a.charAt(0)=='-') {
		sign++;
		a=a.substring(1,a.length());
	}
	if(b.charAt(0)=='-') {
		sign++;
		b=b.substring(1,b.length());
	}
	/////////////////////////
	String max,min;//max:adade bozorgtar az nazare bozorgi;min:adade koochektar az nazare bozorgi;hamvare tak tak arqame "min" dar "max" zarb mishavad
	String answer="0",tmpAns="",zero;//answer:Jabave asli;tmpAns:javabe har marhale;zero:tedade sefre har marhale
	int tmp;//temp baraye ezafe amdan(bozargtar az 9 shodan)
	if(a.length()>=b.length())
	{
		max=a;
		min=b;
	}
	else
	{
		max=b;
		min=a;
	}
	for(i=min.length()-1;i>=0;i--) {
		tmp=0;
		zero="";
		for(j=1;j<min.length()-i;j++)//mohasebe tedad sefre marhale
			zero=zero+"0";
		tmpAns="";
		for(j=max.length()-1;j>=0;j--) {//har raghame min dar har raghame max zarb mishavad
			tmpAns=String.valueOf((((int)max.charAt(j)-48)*((int)min.charAt(i)-48)+tmp)%10)+tmpAns;
			tmp=(((int)max.charAt(j)-48)*((int)min.charAt(i)-48)+tmp)/10;
		}
		if(tmp!=0)//ezafe mande be adad
			tmpAns=String.valueOf(tmp)+tmpAns;
tmpAns+=zero;//sefr ha kenare javabe marhale gozashte mishavd.
		answer=add(answer,tmpAns);//javabe marhale be javabe asli ezafi mishavd
	}
	if(sign%2==0)
	return answer;
	else
		return "-"+answer;
}
/**
 * 
 * @param adade aval
 * @param adade dovom
 * @return String
 */
public static String indexOf(String number1,String number2) {
	int i;
	for(i=0;i<number2.length();i++) {
		if(number2.charAt(i)==number1.charAt(0))
			return String.valueOf(i);
	}
	return "-1";
}

}
