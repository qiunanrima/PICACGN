package okio;

import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: picacg_1.jar:okio/n.class */
public final class n {
    boolean AG;
    boolean AH;
    n AI;
    n AJ;
    final byte[] data;
    int limit;
    int pos;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n() {
        this.data = new byte[8192];
        this.AH = true;
        this.AG = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(n nVar) {
        this(nVar.data, nVar.pos, nVar.limit);
        nVar.AG = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(byte[] bArr, int i, int i2) {
        this.data = bArr;
        this.pos = i;
        this.limit = i2;
        this.AH = false;
        this.AG = true;
    }

    public n a(n nVar) {
        nVar.AJ = this;
        nVar.AI = this.AI;
        this.AI.AJ = nVar;
        this.AI = nVar;
        return nVar;
    }

    public void a(n nVar, int i) {
        if (!nVar.AH) {
            throw new IllegalArgumentException();
        }
        if (nVar.limit + i > 8192) {
            if (nVar.AG) {
                throw new IllegalArgumentException();
            }
            if ((nVar.limit + i) - nVar.pos > 8192) {
                throw new IllegalArgumentException();
            }
            System.arraycopy(nVar.data, nVar.pos, nVar.data, 0, nVar.limit - nVar.pos);
            nVar.limit -= nVar.pos;
            nVar.pos = 0;
        }
        System.arraycopy(this.data, this.pos, nVar.data, nVar.limit, i);
        nVar.limit += i;
        this.pos += i;
    }

    public n au(int i) {
        n gf;
        if (i <= 0 || i > this.limit - this.pos) {
            throw new IllegalArgumentException();
        }
        if (i >= 1024) {
            gf = new n(this);
        } else {
            gf = o.gf();
            System.arraycopy(this.data, this.pos, gf.data, 0, i);
        }
        gf.limit = gf.pos + i;
        this.pos += i;
        this.AJ.a(gf);
        return gf;
    }

    @Nullable
    public n gd() {
        n nVar = this.AI != this ? this.AI : null;
        this.AJ.AI = this.AI;
        this.AI.AJ = this.AJ;
        this.AI = null;
        this.AJ = null;
        return nVar;
    }

    public void ge() {
        if (this.AJ == this) {
            throw new IllegalStateException();
        }
        if (this.AJ.AH) {
            int i = this.limit - this.pos;
            if (i > (8192 - this.AJ.limit) + (this.AJ.AG ? 0 : this.AJ.pos)) {
                return;
            }
            a(this.AJ, i);
            gd();
            o.b(this);
        }
    }
}
