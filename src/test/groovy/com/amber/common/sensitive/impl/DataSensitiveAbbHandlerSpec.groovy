package com.amber.common.sensitive.impl

import spock.lang.Specification
import spock.lang.Unroll

class DataSensitiveAbbHandlerSpec extends Specification {

    @Unroll
    def '#origin is abbreviated to #abb'() {
        def handler = new DataSensitiveAbbHandler()

        expect:
        handler.decrypt(origin) == abb

        where:
        origin                | abb
        null                  | null
        ''                    | ''
        'a'                   | 'a'
        'ab'                  | 'a*'
        'abc'                 | 'a*c'
        'abcd'                | 'a**d'
        '13012345678'         | '130*****678'
        '123456789012345678'  | '123456******345678'
        '这是一段测试文字'       | '这是****文字'
    }

}
