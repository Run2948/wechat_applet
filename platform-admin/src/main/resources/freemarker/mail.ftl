<style type="text/css">  
<!--  
/************ Table ************/  
.xwtable {width: 100%;border-collapse: collapse;border: 1px solid #ccc;}                  
.xwtable thead td {font-size: 12px;color: #333333;text-align: center;background: url(table_top.jpg) repeat-x top center;border: 1px solid #ccc; font-weight:bold;}  
.xwtable tbody tr {background: #fff;font-size: 12px;color: #666666;}             
.xwtable tbody tr.alt-row {background: #f2f7fc;}                 
.xwtable td{line-height:20px;text-align: left;padding:4px 10px 3px 10px;height: 18px;border: 1px solid #ccc;}  
-->  
</style>  
<p>亲爱的管理员您好！</p>
<#if birthdayList??&&(birthdayList?size>0) >
<h3><font color="green">${.now?string("yyyy-MM-dd")}是以下客户生日，请于当天务必完成维护。</h3>
	<table class="xwtable">  
            <thead>  
              <tr>  
               	<td style="width:80px;">用户姓名</td>
               	<td style="width:80px;">用户手机号</td>
                <td style="width:80px;">客户姓名</td>  
                <td style="width:30px;">性别</td>  
                <td style="width:100px;">客户手机号码</td>  
                <td style="width:50px;"> 职务</td>  
                <td>地址</td>  
              </tr>  
            </thead>  
            <tbody>  
		<#list birthdayList as user>
			<tr>  
				<td>${(user.userName)!}</td>  
				<td>${(user.userTel)!}</td>  
				<td>${(user.u_name)!}</td>  
                <td>
                <#if user.gender??>          
			        <#if user.gender=="1">          
			              	男
			         <#else>
					  	女
					</#if>
			     </#if> 
                </td>  
                <td>${(user.mobile)!}</td>  
                <td>${(user.job)!}</td>  
                <td>${(user.address)!}</td> 
			</tr>  
		</#list>
            </tbody>  
          </table>  
</#if>


<#if holidayList??&&(holidayList?size>0) >
<h3><font color="green">15天后的 <#list holidayList as holidayL>${(holidayL.holiday)!}</#list>是中国传统节日<#list holidayList as holidayL>${(holidayL.holiday_name)!}</#list>，请您为后续的代理维护做好准备！ </h3>
	<table class="xwtable">  
            <thead>  
              <tr>  
               <td style="width:80px;">用户姓名</td>
               	<td style="width:80px;">用户手机号</td>
                <td style="width:80px;">客户姓名</td>  
                <td style="width:30px;">性别</td>  
                <td style="width:100px;">客户手机号码</td>  
                <td style="width:50px;"> 职务</td>  
                <td>地址</td>    
              </tr>  
            </thead>  
            <tbody>  
		<#list customerList as customer>
			<tr>  
				<td>${(customer.userName)!}</td>  
				<td>${(customer.userTel)!}</td>  
				<td>${(customer.u_name)!}</td>  
                <td>
                <#if customer.gender??>          
			        <#if customer.gender=="1">          
			              	男
			         <#else>
					  	女
					</#if>
			     </#if> 
                </td>  
                <td>${(customer.mobile)!}</td>  
                <td>${(customer.job)!}</td>  
                <td>${(customer.address)!}</td> 
			</tr>  
		</#list>
            </tbody>  
          </table>  
</#if>
