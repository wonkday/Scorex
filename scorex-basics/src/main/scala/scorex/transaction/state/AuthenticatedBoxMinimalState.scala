package scorex.transaction.state

import scorex.crypto.authds.storage.StorageType
import scorex.crypto.authds.{DataProof, AuthenticatedDictionary}
import scorex.crypto.hash.CryptographicHash
import scorex.transaction.box.Proposition

import scala.util.Try

//for now only boxed state could be Merkelized
trait AuthenticatedBoxMinimalState[L <: Proposition, HashFunction <: CryptographicHash]
  extends BoxMinimalState[L] {

  type ElementProof <: DataProof
  type Storage <: StorageType

  protected val boxesStorage: AuthenticatedDictionary[ElementProof, Storage]

  def digest: HashFunction#Digest

  val hashFunction: HashFunction

  override private[transaction] def rollbackTo(height: Int): Try[AuthenticatedBoxMinimalState[L, HashFunction]]
}