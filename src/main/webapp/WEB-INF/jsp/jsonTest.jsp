<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        window.onload = function () {
            console.log(jsonTest());
        }
    </script>

    <script type="text/javascript">
        function jsonTest() {
            var json = {"depts":[{"id":"11","pid":"2"},{"id":"12","pid":"11"},{"id":"13","pid":"11"},{"id":"14","pid":"12"},{"id":"15","pid":"6"},{"id":"16","pid":"7"}]};

            var jsonData = json['depts'];
            console.log(flat2tree(jsonData))
        }
    </script>

    <script type="text/javascript">
        function flat2tree(jsonData){
            var result = [], temp = {}, i = 0, j = 0, len = jsonData.length
            for(; i < len; i++){
                temp[jsonData[i]['id']] = jsonData[i] // 以id作为索引存储元素，可以无需遍历直接定位元素
            }
            for(; j < len; j++){
                var currentElement = jsonData[j]
                var tempCurrentElementParent = temp[currentElement['pid']] // 临时变量里面的当前元素的父元素
                if (tempCurrentElementParent) { // 如果存在父元素
                    if (!tempCurrentElementParent['childs']) { // 如果父元素没有chindren键
                        tempCurrentElementParent['childs'] = [] // 设上父元素的childs键
                    }
                    tempCurrentElementParent['childs'].push(currentElement) // 给父元素加上当前元素作为子元素
                } else { // 不存在父元素，意味着当前元素是一级元素
                    result.push(currentElement);
                }
            }
            return result;
        }


    </script>

</head>
<body>

</body>
</html>
