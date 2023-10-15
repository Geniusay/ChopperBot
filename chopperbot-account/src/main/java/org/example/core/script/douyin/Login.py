import json
import time

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class dy(object):

    def __init__(self):
        chrome_options = self.options()
        chrome_options.add_experimental_option("excludeSwitches", ["enable-automation"])
        # 解决报错，设置无界面运行
        chrome_options.add_argument('--no-sandbox')
        chrome_options.add_argument('--disable-dev-shm-usage')
        chrome_options.add_argument('window-size=1920x1280')  # 指定浏览器分辨率
        chrome_options.add_argument('--disable-gpu')  # 谷歌文档提到需要加上这个属性来规避bug
        chrome_options.add_argument('--hide-scrollbars')  # 隐藏滚动条, 应对一些特殊页面
        self.browser = webdriver.Chrome(chrome_options)
        self.wait = WebDriverWait(self.browser, 30)
        self.url = "https://www.douyin.com/"

    def options(self):
        chrome_options = webdriver.ChromeOptions()
        return chrome_options

    def click(self, xpath):
        self.wait.until(EC.presence_of_element_located(
            (By.XPATH, xpath))).click()

    def saveCookie(self):
        # 打开浏览器
        self.browser.get(self.url)
        time.sleep(35)
        # 读取cook
        cook = self.browser.get_cookies()
        # 保存cook
        f = open('D:\\Douyincookies.txt', 'w')
        f.write(json.dumps(cook, ensure_ascii=True))  # 格式转化，这不管你是学的那种语言都必须要知道的
        f.close()
        print("success")


if __name__ == '__main__':
    jd = dy()
    jd.saveCookie()
