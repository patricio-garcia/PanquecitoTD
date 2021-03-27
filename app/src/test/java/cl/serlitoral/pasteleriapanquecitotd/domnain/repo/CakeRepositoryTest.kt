package cl.serlitoral.pasteleriapanquecitotd.domnain.repo

import cl.serlitoral.pasteleriapanquecitotd.data.model.Cake
import cl.serlitoral.pasteleriapanquecitotd.domnain.db.CakeEntity
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CakeRepositoryTest {

    @Test
    fun db2Api_happyCase() {
        //Given
        val entity = CakeEntity(1, "Pastel1", "Decripcion1", "23cm", 18900, "img1")
        val expected = Cake(1, "Pastel1", "Decripcion1", "23cm", 18900, "img1")

        //When
        val result = db2api(entity)

        //Then
        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun api2Db_happyCase() {
        //Given
        val cake = Cake(1, "Pastel1", "Decripcion1", "23cm", 18900, "img1")
        val expected = CakeEntity(1, "Pastel1", "Decripcion1", "23cm", 18900, "img1")

        //When
        val result = api2db(cake)

        //Then
        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(expected)
    }
}