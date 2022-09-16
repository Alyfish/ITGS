from bs4 import Beautifulsoup
import requests 

source = requests.get('https://www.facebook.com/groups/ucberkeleyoffcampushousing').text
soup = Beautifulsoup(source, 'lxml')

print(soup.prettify())