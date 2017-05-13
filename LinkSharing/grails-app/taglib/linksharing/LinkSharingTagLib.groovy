package linksharing

import com.ttn.vo.TopicVO

class LinkSharingTagLib {
    static defaultEncodeAs = "raw"
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "ls"
    def isRead = { attrs, body ->
        User user = session.user
        if (user) {
            Long id = attrs.resource.id
            Resource resource = Resource.findById(id)
            if (resource) {
                ReadingItem readingItem = ReadingItem.findByUserAndResource(user,resource)
                if (readingItem) {
                    println readingItem
                if (readingItem.isRead == false) {
                    out << "<a href='${createLink(controller: 'readingItem', action: 'toggleIsRead', id: resource.id)}'>Mark as Read</a> "
                }
            }
            }
        }
    }
    def trendingTopics = { attrs,body ->
        User user = session.user
        List<TopicVO> topicVOList = Topic.getTrendingTopics()
        out << render(template:'/user/trendingTopic', model:[topic:topicVOList])

    }

    def post = {attrs,body ->


    }



}
