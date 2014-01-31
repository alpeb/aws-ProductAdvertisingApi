package macondoventures

import org.scalatest.FunSuite

class SignedRequestsHelperTests extends FunSuite {

  trait UsHelper {
    val helper = new SignedRequestsHelper("AKIAIOSFODNN7EXAMPLE", "1234567890")
  }

  trait UkHelper {
    val helper = new SignedRequestsHelper("AKIAIOSFODNN7EXAMPLE", "1234567890", "ecs.amazonaws.co.uk")
  }

  new UsHelper {
    val signed = helper.sign(Map(
      "Service" -> "AWSECommerceService",
      "Operation" -> "ItemLookup",
      "ItemId" -> "0679722769",
      "ResponseGroup" -> "ItemAttributes,Offers,Images,Reviews",
      "Timestamp" -> "2009-01-01T12:00:00Z",
      "Version" -> "2009-01-06"
    ))

    test("ItemLookup toSign") {
      assert(
        helper.toSign ==
        """GET
webservices.amazon.com
/onca/xml
AWSAccessKeyId=AKIAIOSFODNN7EXAMPLE&ItemId=0679722769&Operation=ItemLookup&ResponseGroup=ItemAttributes%2COffers%2CImages%2CReviews&Service=AWSECommerceService&Timestamp=2009-01-01T12%3A00%3A00Z&Version=2009-01-06"""
      )
    }

    test("ItemLookup signature") {
      assert(
        signed ==
        "http://webservices.amazon.com/onca/xml?AWSAccessKeyId=AKIAIOSFODNN7EXAMPLE" +
        "&ItemId=0679722769&Operation=ItemLookup&ResponseGroup=It" +
        "emAttributes%2COffers%2CImages%2CReviews&Service=AWSECommerceServ" +
        "ice&Timestamp=2009-01-01T12%3A00%3A00Z&Version=2009-01-06&Signatu" +
        "re=M%2Fy0%2BEAFFGaUAp4bWv%2FWEuXYah99pVsxvqtAuC8YN7I%3D"
      )
    }
  }

  new UkHelper {
    val signed = helper.sign(Map(
      "Service" -> "AWSECommerceService",
      "Operation" -> "ItemSearch",
      "Actor" -> "Johnny Depp",
      "ResponseGroup" -> "ItemAttributes,Offers,Images,Reviews,Variations",
      "SearchIndex" -> "DVD",
      "Sort" -> "salesrank",
      "AssociateTag" -> "mytag-20",
      "Timestamp" -> "2009-01-01T12:00:00Z",
      "Version" -> "2009-01-01"
    ))

    test("ItemSearch toSign") {
      assert(
        helper.toSign ==
        """GET
ecs.amazonaws.co.uk
/onca/xml
AWSAccessKeyId=AKIAIOSFODNN7EXAMPLE&Actor=Johnny%20Depp&AssociateTag=mytag-20&Operation=ItemSearch&ResponseGroup=ItemAttributes%2COffers%2CImages%2CReviews%2CVariations&SearchIndex=DVD&Service=AWSECommerceService&Sort=salesrank&Timestamp=2009-01-01T12%3A00%3A00Z&Version=2009-01-01"""
      )
    }

    test("ItemSearch signature") {
      assert(
        signed ==
        "http://ecs.amazonaws.co.uk/onca/xml?AWSAccessKeyId=AKIAIOSFODNN7EXAMPLE" +
        "&Actor=Johnny%20Depp&AssociateTag=mytag-20&Operation=ItemSe" +
        "arch&ResponseGroup=ItemAttributes%2COffers%2CImages%2CReviews%2CV" +
        "ariations&SearchIndex=DVD&Service=AWSECommerceService&" + 
        "Sort=salesrank&Ti" +
        "mestamp=2009-01-01T12%3A00%3A00Z&Version=2009-01-01&" +
        "Signature=T" +
        "uM6E5L9u%2FuNqOX09ET03BXVmHLVFfJIna5cxXuHxiU%3D"
      )
    }
  }

  new UsHelper {
    val signed = helper.sign(Map(
      "Service" -> "AWSECommerceService",
      "Operation" -> "CartCreate",
      "Item.1.OfferListingId" -> "j8ejq9wxDfSYWf2OCp6XQGDsVrWhl08GSQ9m5j+e8MS449BN1XGUC3DfU5Zw4nt/FBt87cspLow1QXzfvZpvzg==",
      "Item.1.Quantity" -> "3",
      "AssociateTag" -> "mytag-20",
      "Timestamp" -> "2009-01-01T12:00:00Z",
      "Version" -> "2009-01-01"
    ))

    test("CartCreate toSign") {
      assert(
        helper.toSign ==
        """GET
webservices.amazon.com
/onca/xml
AWSAccessKeyId=AKIAIOSFODNN7EXAMPLE&AssociateTag=mytag-20&Item.1.OfferListingId=j8ejq9wxDfSYWf2OCp6XQGDsVrWhl08GSQ9m5j%2Be8MS449BN1XGUC3DfU5Zw4nt%2FFBt87cspLow1QXzfvZpvzg%3D%3D&Item.1.Quantity=3&Operation=CartCreate&Service=AWSECommerceService&Timestamp=2009-01-01T12%3A00%3A00Z&Version=2009-01-01"""
      )
    }
    
    test("CartCreate signature") {
      assert(
        signed ==
        "http://webservices.amazon.com/onca/xml?AWSAccessKeyId=AKIAIOSFODNN7EXAMPLE" +
        "&AssociateTag=mytag-20&Item.1.OfferListingId=j8ejq9wxDfSYWf2O" +
        "Cp6XQGDsVrWhl08GSQ9m5j%2Be8MS449BN1XGUC3DfU5Zw4nt%2FFBt87cspLow1Q" +
        "XzfvZpvzg%3D%3D&Item.1.Quantity=3&Operation=CartCreate&Service=AW" +
        "SECommerceService&" +
        "Timestamp=2009-01-01T12%3A00%3A00Z&Version=2009-0" +
        "1-01&" +
        "Signature=cF3UtjbJb1%2BxDh387C%2FEmS1BCtS%2FZ01" +
        "taykBCGemvUU%3D"
      )
    }
  }

  new UsHelper {
    val signed = helper.sign(Map(
      "Service" -> "AWSECommerceService",
      "Operation" -> "BrowseNodeLookup",
      "BrowseNodeId" -> "465600",
      "AssociateTag" -> "mytag-20",
      "ResponseGroup" -> "BrowseNodeInfo,TopSellers,NewReleases,MostWishedFor,MostGifted",
      "Timestamp" -> "2009-01-01T12:00:00Z",
      "Version" -> "2009-01-01"
    ))

    test("BrowseNodeLookup toSign") {
      assert(
        helper.toSign ==
        """GET
webservices.amazon.com
/onca/xml
AWSAccessKeyId=AKIAIOSFODNN7EXAMPLE&AssociateTag=mytag-20&BrowseNodeId=465600&Operation=BrowseNodeLookup&ResponseGroup=BrowseNodeInfo%2CTopSellers%2CNewReleases%2CMostWishedFor%2CMostGifted&Service=AWSECommerceService&Timestamp=2009-01-01T12%3A00%3A00Z&Version=2009-01-01"""
      )
    }

    test("BrowseNodeLookup signature") {
      assert(
        signed ==
        "http://webservices.amazon.com/onca/xml?AWSAccessKeyId=AKIAIOSFODNN7EXAMPLE" +
        "&AssociateTag=mytag-20&BrowseNodeId=465600&Operation=BrowseNo" +
        "deLookup&ResponseGroup=BrowseNodeInfo%2CTopSellers%2CNewReleases%" +
        "2CMostWishedFor%2CMostGifted&Service=AWSECommerceService&" +
        "Timestamp=2009" +
        "-01-01T12%3A00%3A00Z&Version=2009-01-01&" +
        "Signatur" +
        "e=kEXxAIqhh6eBhLhrVMz2gt3ocMaH%2FOBVPbjvc9TG8ao%3D"
      )
    }
  }

  new UsHelper {
    val signed = helper.sign(Map(
      "Service" -> "AWSECommerceService",
      "Operation" -> "SimilarityLookup",
      "ItemId" -> "B0011ZK6PC,B000NK8EWI",
      "AssociateTag" -> "mytag-20",
      "ResponseGroup" -> "Offers,ItemAttributes",
      "SimilarityType" -> "Intersection",
      "Condition" -> "New",
      "Merchant" -> "Amazon",
      "Timestamp" -> "2009-01-01T12:00:00Z",
      "Version" -> "2009-01-01"
    ))

    test("SimilarityLookup toSign") {
      assert(
        helper.toSign ==
        """GET
webservices.amazon.com
/onca/xml
AWSAccessKeyId=AKIAIOSFODNN7EXAMPLE&AssociateTag=mytag-20&Condition=New&ItemId=B0011ZK6PC%2CB000NK8EWI&Merchant=Amazon&Operation=SimilarityLookup&ResponseGroup=Offers%2CItemAttributes&Service=AWSECommerceService&SimilarityType=Intersection&Timestamp=2009-01-01T12%3A00%3A00Z&Version=2009-01-01"""
      )
    }

    test("SimilarityLookup signature") {
      assert(
        signed ==
        "http://webservices.amazon.com/onca/xml?AWSAccessKeyId=AKIAIOSFODNN7EXAMPLE" +
        "&AssociateTag=mytag-20&Condition=New&ItemId=B0011ZK6PC%2CB000" +
        "NK8EWI&Merchant=Amazon&Operation=SimilarityLookup&ResponseGroup=O" +
        "ffers%2CItemAttributes&Service=AWSECommerceService&" +
        "SimilarityType=Inter" +
        "section&Timestamp=2009-01-01T12%3A00%3A00Z&Version=2009-01-01&" +
        "Signature=I2pb" +
        "qxuS%2FmZK6Apwz0oLBxJn2wDL5n4kFQhgYWgLM7I%3D"
      )
    }
  }
}
