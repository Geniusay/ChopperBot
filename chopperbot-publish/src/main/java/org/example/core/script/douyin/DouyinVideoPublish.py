import sys

import time

from selenium.webdriver.chrome import webdriver
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class douyin(object):

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

    def puiblish(self):
        cookies = sys.argv[0]
        self.browser.get(self.url)
        self.browser.delete_all_cookies()
        for cookie in cookies:
            self.browser.add_cookie(cookie)
        self.browser.implicitly_wait(10)
        time.sleep(2)
        self.browser.refresh()
        self.browser.get(self.url)
        userMsg = '/html/body/div[2]/div[1]/div[4]/div[2]/div[1]/header/div/div/div[2]/div/div/div[6]/div/a'
        loginFlag = self.wait.until(EC.presence_of_element_located(
            (By.XPATH, userMsg)))
        if not loginFlag is None:
            self.click(userMsg)
            time.sleep(2)

            videoPage = '/html/body/div[2]/div[1]/div[3]/div[3]/div/div/div[3]/div[2]/div[2]/div[1]/div[2]/div/div[1]/a'
            self.click(videoPage)
            all_h = self.browser.window_handles
            self.browser.switch_to.window(all_h[1])
            h2 = self.browser.current_window_handle
            send_video = "/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[1]/button"
            self.click(send_video)
            time.sleep(2)
            video_path = '/html/body/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div/div/div[3]/div/div[1]/div/div[1]/div/label/input'
            filePath = sys.argv[1]
            self.wait.until(EC.presence_of_element_located((By.XPATH, video_path))).send_keys(filePath)
            time.sleep(2)
            video_title = '/html/body/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div/div/div[2]/div[1]/div[2]/div/div/div/div[1]/div'
            self.wait.until(EC.presence_of_element_located((By.XPATH, video_title))).send_keys(
                sys.argv[2])
            push_video = '/html/body/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div/div/div[2]/div[1]/div[18]/button[1]'
            time.sleep(2)
            self.click(push_video)
            time.sleep(2)
        else:
            print("error")

if __name__ == '__main__':
    jd = douyin()
    jd.puiblish()
