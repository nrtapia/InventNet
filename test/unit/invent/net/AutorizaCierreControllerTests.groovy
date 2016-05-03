package invent.net



import org.junit.*
import grails.test.mixin.*

@TestFor(AutorizaCierreController)
@Mock(AutorizaCierre)
class AutorizaCierreControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/autorizaCierre/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.autorizaCierreInstanceList.size() == 0
        assert model.autorizaCierreInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.autorizaCierreInstance != null
    }

    void testSave() {
        controller.save()

        assert model.autorizaCierreInstance != null
        assert view == '/autorizaCierre/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/autorizaCierre/show/1'
        assert controller.flash.message != null
        assert AutorizaCierre.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/autorizaCierre/list'


        populateValidParams(params)
        def autorizaCierre = new AutorizaCierre(params)

        assert autorizaCierre.save() != null

        params.id = autorizaCierre.id

        def model = controller.show()

        assert model.autorizaCierreInstance == autorizaCierre
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/autorizaCierre/list'


        populateValidParams(params)
        def autorizaCierre = new AutorizaCierre(params)

        assert autorizaCierre.save() != null

        params.id = autorizaCierre.id

        def model = controller.edit()

        assert model.autorizaCierreInstance == autorizaCierre
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/autorizaCierre/list'

        response.reset()


        populateValidParams(params)
        def autorizaCierre = new AutorizaCierre(params)

        assert autorizaCierre.save() != null

        // test invalid parameters in update
        params.id = autorizaCierre.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/autorizaCierre/edit"
        assert model.autorizaCierreInstance != null

        autorizaCierre.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/autorizaCierre/show/$autorizaCierre.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        autorizaCierre.clearErrors()

        populateValidParams(params)
        params.id = autorizaCierre.id
        params.version = -1
        controller.update()

        assert view == "/autorizaCierre/edit"
        assert model.autorizaCierreInstance != null
        assert model.autorizaCierreInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/autorizaCierre/list'

        response.reset()

        populateValidParams(params)
        def autorizaCierre = new AutorizaCierre(params)

        assert autorizaCierre.save() != null
        assert AutorizaCierre.count() == 1

        params.id = autorizaCierre.id

        controller.delete()

        assert AutorizaCierre.count() == 0
        assert AutorizaCierre.get(autorizaCierre.id) == null
        assert response.redirectedUrl == '/autorizaCierre/list'
    }
}
