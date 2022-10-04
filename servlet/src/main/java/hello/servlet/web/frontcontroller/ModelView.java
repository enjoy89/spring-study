package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {

    private String viewName;    // 뷰 이름
    private Map<String, Object> model = new HashMap<>();    // 뷰를 렌더링할 때 필요한 model 객체
    // model은 단순히 map으로 되어 있어 컨트롤러에서 뷰에 필요한 정보를 key, value 형태로 넣어주면 된다.

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
