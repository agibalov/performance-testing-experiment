import locust


class UserBehavior(locust.TaskSet):
    @locust.task(5)
    def index(self):
        self.client.get('/')
        self.client.get('/Content/todo.png')
        self.client.get('/Content/retasklogo.png')
        self.client.get('/Content/favicon.ico')

    @locust.task(1)
    def disclaimer(self):
        self.client.get('/Home/Disclaimer')


class WebsiteUser(locust.HttpLocust):
    task_set = UserBehavior
    min_wait = 1000
    max_wait = 1000
