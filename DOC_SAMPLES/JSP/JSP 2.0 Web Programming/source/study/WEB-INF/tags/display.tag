<%@ tag body-content="scriptless" pageEncoding="euc-kr" %>
<%@ attribute name="color" %>
<%@ attribute name="bgcolor" %>
<%@ attribute name="title" %>

<TABLE border="0" bgcolor="${color}">
  <TR>
    <TD><B>${title}</B></TD>
  </TR>
  <TR>
    <TD bgcolor="${bgcolor}">
      <jsp:doBody/>
    </TD>
  </TR>
</TABLE>