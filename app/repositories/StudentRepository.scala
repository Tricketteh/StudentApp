package repositories

import javax.inject.Inject

import models.Student
import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import org.mongodb.scala.bson.ObjectId

class StudentRepository @Inject()(mongoClient: MongoClient) {

  private val database: MongoDatabase = mongoClient.getDatabase("studentdb")
  private val collection: MongoCollection[Student] = database.getCollection("students")

  def findAll(): Future[Seq[Student]] = collection.find().toFuture()

  def findById(id: String): Future[Option[Student]] = {
    collection.find(equal("_id", new ObjectId(id))).headOption()
  }

  def create(student: Student): Future[Unit] = {
    collection.insertOne(student).toFuture().map(_ => ())
  }

  def update(id: String, student: Student): Future[Unit] = {
    collection.replaceOne(equal("_id", new ObjectId(id)), student).toFuture().map(_ => ())
  }

  def delete(id: String): Future[Unit] = {
    collection.deleteOne(equal("_id", new ObjectId(id))).toFuture().map(_ => ())
  }
}