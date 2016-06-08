package com.oxygenxml.examples.webdav;

import java.util.HashMap;

import javax.servlet.ServletException;

import ro.sync.ecss.extensions.api.webapp.plugin.PluginConfigExtension;

/**
 * Plugin extension used to handle the configuration of this plugin.
 */
public class WebdavPluginConfigExtension extends PluginConfigExtension {

  /**
   * Flag indicating whether documents should be locked on open.
   */
  final static String LOCKING_ENABLED = "webdav.lock_on_open";
  
  final static String ENFORCED_URL = "webdav.enforced_url";
  
  @Override
  public void init() throws ServletException {
    super.init();
    HashMap<String, String> defaultOptions = new HashMap<String, String>();
    defaultOptions.put(LOCKING_ENABLED, "on");
    defaultOptions.put(ENFORCED_URL, "");
    
    setDefaultOptions(defaultOptions);
  }
  
  @Override
  public String getPath() {
    return "webdav-config";
  }
  
  /**
   * @see ro.sync.ecss.extensions.api.webapp.plugin.PluginConfigExtension#getOptionsForm()
   */
  @Override
  public String getOptionsForm() {
    String optionValue = getOption(LOCKING_ENABLED, "on");
    boolean isLockEnabled = "on".equals(optionValue);
    
    String enforcedUrl = getOption(ENFORCED_URL, "");
    
    return "<div style='font-family:robotolight, Arial, Helvetica, sans-serif;font-size:0.85em;font-weight: lighter'>"
            + "<form style='text-align:left;line-height: 1.7em;'>"
              + "<label style='margin-bottom:6px;display:block;overflow:hidden'>"
                + "<input name='" + LOCKING_ENABLED + "' type=\"checkbox\" value=\"on\"" + 
                      (isLockEnabled ? "checked" : "") + "> Lock resources on open"
              + "</label>"

              + "<label style='margin-top:6px;display:block;overflow:hidden'>"
                + "Imposed server: "
                + "<input placeholder='Enforced URL' name='" + ENFORCED_URL
                + "' type='text' style='color:#606060;background-color:#FAFAFA;"
                + "-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;display: inline-block;"
                + "width:75%;border-radius:4px;border:1px solid #E4E4E4;padding:6px 4px' value='" + enforcedUrl + "'/>"
              + "</label>"
              + "<div style='background-color: lightyellow;border: 1px solid #dadab4; padding: 8px;margin-top: 5px;'>"
              + "Note: Once a server is imposed the user will be able to browse only the enforced servers "
              + "set in the above input or by other plugins through the javascript API."
              + "</div>"
            + "</form>"
          + "</div>";
  }

  @Override
  protected void setOption(String key, String value) {
    super.setOption(key, value);
  }
  
  /**
   * @see ro.sync.ecss.extensions.api.webapp.plugin.PluginConfigExtension#getOptionsJson().
   */
  @Override
  public String getOptionsJson() {
    return "{"
        + "\"enforced_webdav_server\":\"" + getOption(ENFORCED_URL, "") + "\"," 
        + "\"lock_on_open\":\"" + getOption(LOCKING_ENABLED, "on")
        + "\"}";
  }
}
