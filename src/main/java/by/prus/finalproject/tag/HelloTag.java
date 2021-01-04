package by.prus.finalproject.tag;

import by.prus.finalproject.bean.Client;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HelloTag extends TagSupport {

    private Client client;

    @Override
    public int doStartTag() throws JspException {
        String welocmeMessage = String.format("Hello, dear %s. \n Your orders are:",client.getName());
        try {
            pageContext.getOut().write("<div>"+welocmeMessage+"</div>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
