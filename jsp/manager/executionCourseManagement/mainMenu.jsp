<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %><%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%><center>	<img alt=""  src="<%= request.getContextPath() %>/images/logo-fenix.gif" width="100" height="100"/></center>

<div style="font-size: 1.20em;">
<p><strong>&raquo; 	<html:link module="/manager" module="/manager" page="/executionCourseManagement.do?method=mainPage">		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.manager.mainPage" />	</html:link></strong></p><h2><bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.manager.executionCourseManagement"/></h2><p><strong>&raquo; 	<html:link module="/manager" module="/manager" page="<%="/insertExecutionCourse.do?method=prepareInsertExecutionCourse"%>">		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.manager.executionCourseManagement.insert.executionCourse" />	</html:link></strong></p><p><strong>&raquo; 	<html:link module="/manager" module="/manager" page="<%="/editExecutionCourseChooseExPeriod.do?method=prepareEditECChooseExecutionPeriod"%>">		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.manager.executionCourseManagement.edit.executionCourse" />	</html:link></strong></p><%--<p><strong>&raquo; 	<html:link module="/manager" module="/manager" page="/readExecutionPeriods.do">		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.manager.insert.executionCourse" />	</html:link></strong></p>--%><p><strong>&raquo; 	<html:link module="/manager" module="/manager" page="/chooseDegreesForExecutionCourseMerge.do?method=prepareChooseDegreesAndExecutionPeriod">		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.manager.executionCourseManagement.join.executionCourse" />	</html:link></strong></p><p><strong>&raquo; 
	<html:link module="/manager" module="/manager" page="/executionCourseManagement/createCourseReportsForExecutionPeriod.faces">
		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="link.manager.createCourseReports" />
	</html:link>
</strong></p>

<p><strong>&raquo; 
	<html:link module="/manager" module="/manager" page="/createExecutionCourses.do?method=chooseDegreeType">
		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="link.manager.createExecutionCourses" />
	</html:link>
</strong></p>

</div>