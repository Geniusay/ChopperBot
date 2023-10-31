package org.example.service;



import org.example.api.AccountApi;
import org.example.api.LabelManagerPluginApi;
import org.example.api.OpenAPIPluginApi;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/12 22:26
 */
public interface AccountService {

    AccountApi accountPlugin();

    OpenAPIPluginApi chatGptPlugin();

    LabelManagerPluginApi labelManagerPlugin();
}
