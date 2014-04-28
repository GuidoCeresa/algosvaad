package algosvaad



import grails.test.mixin.*
import spock.lang.*

@TestFor(VersioneController)
@Mock(Versione)
class VersioneControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.versioneInstanceList
        model.versioneInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.versioneInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        def versione = new Versione()
        versione.validate()
        controller.save(versione)

        then: "The create view is rendered again with the correct model"
        model.versioneInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        versione = new Versione(params)

        controller.save(versione)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/versione/show/1'
        controller.flash.message != null
        Versione.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def versione = new Versione(params)
        controller.show(versione)

        then: "A model is populated containing the domain instance"
        model.versioneInstance == versione
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def versione = new Versione(params)
        controller.edit(versione)

        then: "A model is populated containing the domain instance"
        model.versioneInstance == versione
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/versione/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def versione = new Versione()
        versione.validate()
        controller.update(versione)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.versioneInstance == versione

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        versione = new Versione(params).save(flush: true)
        controller.update(versione)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/versione/show/$versione.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/versione/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def versione = new Versione(params).save(flush: true)

        then: "It exists"
        Versione.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(versione)

        then: "The instance is deleted"
        Versione.count() == 0
        response.redirectedUrl == '/versione/index'
        flash.message != null
    }
}
