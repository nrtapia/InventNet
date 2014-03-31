package invent.net



import org.junit.*
import grails.test.mixin.*

@TestFor(CierreDiarioController)
@Mock(CierreDiario)
class CierreDiarioControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cierreDiario/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cierreDiarioInstanceList.size() == 0
        assert model.cierreDiarioInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.cierreDiarioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cierreDiarioInstance != null
        assert view == '/cierreDiario/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cierreDiario/show/1'
        assert controller.flash.message != null
        assert CierreDiario.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cierreDiario/list'


        populateValidParams(params)
        def cierreDiario = new CierreDiario(params)

        assert cierreDiario.save() != null

        params.id = cierreDiario.id

        def model = controller.show()

        assert model.cierreDiarioInstance == cierreDiario
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cierreDiario/list'


        populateValidParams(params)
        def cierreDiario = new CierreDiario(params)

        assert cierreDiario.save() != null

        params.id = cierreDiario.id

        def model = controller.edit()

        assert model.cierreDiarioInstance == cierreDiario
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cierreDiario/list'

        response.reset()


        populateValidParams(params)
        def cierreDiario = new CierreDiario(params)

        assert cierreDiario.save() != null

        // test invalid parameters in update
        params.id = cierreDiario.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cierreDiario/edit"
        assert model.cierreDiarioInstance != null

        cierreDiario.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cierreDiario/show/$cierreDiario.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cierreDiario.clearErrors()

        populateValidParams(params)
        params.id = cierreDiario.id
        params.version = -1
        controller.update()

        assert view == "/cierreDiario/edit"
        assert model.cierreDiarioInstance != null
        assert model.cierreDiarioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cierreDiario/list'

        response.reset()

        populateValidParams(params)
        def cierreDiario = new CierreDiario(params)

        assert cierreDiario.save() != null
        assert CierreDiario.count() == 1

        params.id = cierreDiario.id

        controller.delete()

        assert CierreDiario.count() == 0
        assert CierreDiario.get(cierreDiario.id) == null
        assert response.redirectedUrl == '/cierreDiario/list'
    }
}
