package com.example.exercise.wiremock

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.Options
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.github.tomakehurst.wiremock.http.HttpHeader
import com.github.tomakehurst.wiremock.http.HttpHeaders
import com.github.tomakehurst.wiremock.junit.WireMockClassRule
import org.assertj.core.api.Assertions.*
import org.junit.*
import java.nio.charset.Charset

class BasicMockExerciseTests {

    companion object {
        val config: Options = options().apply {
            port(8089)
        }
        @ClassRule
        @JvmField
        val wireMockClassRule: WireMockClassRule = WireMockClassRule(options().port(8089))

        @BeforeClass
        @JvmStatic
        fun classSetup() {
            println("class setup")
        }

        @AfterClass
        @JvmStatic
        fun classTearDown() {
            println("class tearDown")
        }
    }

    //@Rule
    //@JvmField
    //val wireMockRule: WireMockRule = WireMockRule(options().port(8089))

    @Before
    fun setup() {
        println("setup")
    }

    @After
    fun tearDown() {
        println("tearDown")
    }

    /**
     * シンプルなGetリクエストのサンプル
     */
    @Test
    fun `test simple get example`() {
        // setup
        val responseData = """
            |{
            |   "id": 77,
            |    "name": "john",
            |    "age": 20
            |}
            """.trimMargin()

        // urlEqualTo("/my/member/77")
        // urlPathEqualTo("/my/member/77")
        // urlMatching("/my/member/[0-9]*")
        // urlPathMatching("/my/member/[0-9]*")
        // anyUrl()
        stubFor(get(urlPathMatching("/my/member/[0-9]*"))
            .willReturn(
                okJson(responseData)
                .withFixedDelay(1000 * 30)
            )
        )

        // exercise
        // テスト対象コードの実行
        val result = getSomething("/my/member/77")

        // verify
        // テスト対象コードの検証
        assertThat(result).contains("\"id\": 77", "\"name\": \"john\"", "\"age\": 20")

        // APIが1回呼ばれること
        verify(1, getRequestedFor(urlPathEqualTo("/my/member/77")))

        // リクエストヘッダーのAcceptがapplication/jsonであること
        verify(getRequestedFor(urlPathEqualTo("/my/member/77"))
            .withHeader("Accept", matching("application/json"))
        )
    }

    /**
     * テキストファイルを使用するサンプル
     */
    @Test
    fun `test get example with text file`() {
        // setup
        val headers = HttpHeaders()
                .plus(HttpHeader("Content-Type", "application/json"))
                .plus(HttpHeader("Cache-Control", "no-cache"))

        stubFor(get(urlPathEqualTo("/my/member/88"))
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeaders(headers)
                                .withBodyFile("person.json")
                                .withFixedDelay(1000 * 10)
                )
        )

        // exercise
        // テスト対象コードの実行
        val result = getSomething("/my/member/88")

        // verify
        // テスト対象コードのassertion
        assertThat(result).contains("\"id\": 88", "\"name\": \"tom\"", "\"age\": 40")

        verify(1, getRequestedFor(urlPathEqualTo("/my/member/88")))

        verify(getRequestedFor(urlPathEqualTo("/my/member/88"))
                .withHeader("Accept", matching("application/json"))
        )
    }

    /**
     * 少し複雑なGetリクエストのサンプル
     */
    @Test
    fun `test complex get example`() {
        // setup
        val responseData = """
            |{
            |    "id": 99,
            |    "name": "jack",
            |    "age": 30
            |}
            """.trimMargin()

        stubFor(get(urlPathMatching("/my/member/[0-9]*"))
            .withQueryParam("ref", matching("[a-zA-Z]*"))
            .withHeader("Accept", equalTo("application/json"))
            .withHeader("User-Agent", equalTo("Mozilla/5.0"))
            .withHeader("X-TEST-Header", equalToIgnoreCase("custom test header"))
            .withCookie("session", equalTo("1234-5678-9012"))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withHeader("Cache-Control", "no-cache")
                    .withHeader("X-TEST-Options", "debug")
                    .withBody(responseData)
             )
        )

        // exercise
        // テスト対象コードの実行
        val result = getSomething("/my/member/99", listOf("ref" to "YDpXhvOfkR"))

        // verify
        // テスト対象コードの検証
        assertThat(result).contains("\"id\": 99", "\"name\": \"jack\"", "\"age\": 30")

        verify(1, getRequestedFor(urlPathEqualTo("/my/member/99")))

        verify(getRequestedFor(urlPathMatching("/my/member/[0-9]*"))
            .withQueryParam("ref", matching("[a-zA-Z]*"))
            .withHeader("Accept", matching("application/json"))
        )
    }

    /**
     * BAD Requestのサンプル
     */
    @Test
    fun `test bat request example`() {
        // setup
        stubFor(get(urlPathEqualTo("/my/member/abc"))
            .willReturn(
                badRequest()
            )
        )

        // exercise & verify
        // テスト対象コードの実行
        assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy {
            getSomething("/my/member/abc")
        }
        .withMessage("api call failure")

        verify(1, getRequestedFor(urlPathEqualTo("/my/member/abc")))

        verify(getRequestedFor(urlPathEqualTo("/my/member/abc"))
            .withHeader("Accept", matching("application/json"))
        )
    }

    /**
     * シンプルなPostリクエストのサンプル
     */
    @Test
    fun `test simple post example`() {
        // setup
        val postData = """
            |{
            |    "id": 1220,
            |    "name": "jack",
            |    "age": 50
            |}
            """.trimMargin()

        // urlPathEqualTo("/my/member")
        stubFor(post(urlPathEqualTo("/my/member"))
            .withHeader("Content-Type", equalTo("application/json"))
            .withRequestBody(
                matchingJsonPath("name", equalToIgnoreCase("jack"))
                //equalToJson("{\"id\":120, \"name\":\"jack\", \"age\":50}")
            )
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withBody("success")
            )
        )

        // exercise
        val result = postSomething("/my/member", postData)

        // verify
        // テスト対象コードのassertion
        assertThat(result).isEqualTo("success")

        verify(1, postRequestedFor(urlPathEqualTo("/my/member")))

        verify(postRequestedFor(urlPathEqualTo("/my/member"))
                .withHeader("Content-Type", matching("application/json"))
        )
    }

    /**
     *  /member/{id}
     *
     */
    fun getSomething(urlPath: String, params: List<Pair<String, String>> = emptyList()): String {
        val (_, res, result) = "http://localhost:8089$urlPath".httpGet(params)
                .header("Accept" to "application/json")
                .header("User-Agent" to "Mozilla/5.0")
                .header("X-TEST-Header" to "custom test header")
                .header("Cookie" to "session=1234-5678-9012")
                //.timeout(5000)
                .timeoutRead(1000 * 60)
                .responseString()

        return when(result) {
            is Result.Success -> {
                // for debug
                res.headers["Content-Type"]?.let { println(it) }
                res.headers["Cache-Control"]?.let { println(it) }
                res.headers["X-TEST-Options"]?.let { println(it) }
                result.get().also { println(it) }
            }
            is Result.Failure -> {
                error("api call failure")
            }
        }
    }

    fun postSomething(urlPath: String, body: String = "{}", params: List<Pair<String, String>> = emptyList()): String {
        val (_, _, result) = "http://localhost:8089$urlPath".httpPost(params)
                .header("Content-Type" to "application/json")
                .header("User-Agent" to "Mozilla/5.0")
                .header("X-TEST-Header" to "custom test header")
                .body(body, Charset.defaultCharset())
                .responseString()

        return when(result) {
            is Result.Success -> {
                result.get().also { println(it) }
            }
            is Result.Failure -> {
                error("api call failure")
            }
        }
    }

}